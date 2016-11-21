 Computer Systems Architecture Project
====================

## Instruction

[CSCI 6461](http://home.gwu.edu/~mlancast/CSCI6461Section10Fall2016/) programmed computer simulation of a small processor with performance improvements with Java.


## Part 1 (finished)

Design and implement the basic machine architecture

Implement a simple memory

Execute Load and Store instructions

Build initial user interface to simulator

## Part 2 (finished)

Design and implement the modules for enhanced memory and cache operations

Implement all instructions except for MSR, CHK, and 

Floating Point/Vector operations

Extend the user interface

Demonstrate 1st program running on your simulator

## Part 3 (finished)

Make sure all instructions (as specified below) execute on your simulator

Demonstrate 2nd program running on your simulator

## Part 4 (current)

Design and implement the modules for floating point and vector operations and simple pipelining; extend the user interface

Design and implement simple branch prediction and speculative execution, trap if an error occurs to an error handling routine

## 5-level Pipeline Design of Y16

### Introduction

The CPU simulator is 16-bit which called Y16 (Y64 is 64-bit CPU simulator in [CSAPP](http://www.csapp.cs.cmu.edu)) and has a different instruction sets from ISA.

|0-5|6-7|8-9|10|11-15|
|---|---|---|---|---|
|icode|rA|rB/rX|I|valC|

<center>Table 1. Structure of Instruction in Y16</center>

### Load/Store Instructions

In Y16, we have two types of general propose registers (in the document, they are general propose registers and index registers, but we want to simplify the two kinds of registers into one type in Y64).

|Y16 Registers|Y64 Registers|Code|
|---|---|---|
|GPR0|%eax|0x00|
|GPR1|%ebx|0x01|
|GPR2|%ecx|0x02|
|GPR3|%ebx|0x03|
|IX1|%ix1|0x10|
|IX2|%ix2|0x11|
|IX3|%ix3|0x12|


<center>Table 2. Registers in Y16 </center>

Then, Y16 also have load/store instructions with memory, register, and immediate like `xxmovl` instructions in Y64. In Y16, instructions however have two main differences. First, rB and rA use the same bits. Second, they have one indirected bit which Y64 doesn't have.

The index registers and indirect bit are used to calculate the effective address. Thus different from classic 5-level pipeline, we need to add effective address calculation stage.

```python
int ea_valEA = [
	# no indirect addressing
	E_i == 0 & ~E_hasIX : valC;
	E_i == 0 & E_hasIX : valC + valB;
	# indirect addressing
	E_i == 1 & ~E_hasIX : C(valC);
	E_i == 1 & E_hasIX : C(valC + valB);
];
```
The instructions of load/store operations.

|icode|Instruction|Y16 Function|Y64 Funcion|Indirected Bit|
|---|---|---|---|---|
|001|LDR|M -> R|mrmovl|Yes, EA or C(EA)|
|002|STR|R -> M|rmmovl|Yes, EA or C(EA)|
|003|LDA|I -> R|irmovl|Yes, EA or C(EA), no fetching|
|004|AMR|R+M -> R|opl, irmovl|No, EA|
|005|SMR|R-M -> R|opl, irmovl|No, EA|
|006|AIR|R+I -> R|opl, irmovl|No, EA, no fetching|
|007|SIR|R-I -> R|opl, irmovl|No, EA, no fetching|
|041|LDX|M -> IX|mrmovl|Yes, EA or C(EA)|
|042|STX|IX -> M|rmmovl|Yes, EA or C(EA)|

<center>Table 3. Load/Store Instruction in Y16</center>

Y16 also defines machine status register to display the health of machine. So we define the machine status as follow:

|Status Name|Meaning|Value|
|---|---|---|
|STAT_BUB||0|
|STAT_AOK|Normal operation|1|
|STAT_HLT|Halt instruction|2|
|STAT_ADR|Invalid address encountered|3|
|STAT_INS|Invalid Instruction|4|
|STAT_PIP||5|

<center>Table 4. Machine Status in Y16</center>

### Arithmetic Instruction

The arithmetic instructions have one or two operands and the result always write back to register of first operand.

|icode|Instruction|Number of Operands|Y16 Function|
|---|----|---|---|
|020|MLT|Rx, Ry| Rx * Ry -> Rx, Rx+1|
|021|DVD|Rx, Ry| Rx / Ry -> Rx, Rx+1|
|022|TRR|Rx, Ry| If Rx == Ry, cc(4) = 1; else cc(4) = 0|
|023|AND|Rx, Ry| Rx & Ry -> Rx|
|024|ORR|Rx, Ry| Rx | Ry -> Rx|
|025|NOT|Rx| ~Rx -> Rx|

<center>Table 5. Instructions of Arithmetic</center>

> It's weird there isn't ADD or SUB instruction :(



### 5 Stages of Pipelining

This project involves 5-level pipeline which contains fetch stage, decode stage, execute stage, memory stage, and write back stage.


#### 1. Fetch Stage

Instructions which needs `rA` and `rB`/`rX`:


Instructions which needs `valC`:



#### 2. Decode Stage

`D_srcA` means the value of `D_valA` will fetch from which register:

```python
int D_srcA = [
	d_icode in {I_STR, I_LDA, I_AMR, I_SMR, I_AIR, I_SIR} : D_rA;
	1 : R_NONE;
];
```

`D_srcB` means the value of `D_valB` will fetch from which register:

```python
int D_srcB = [
	d_icode in {I_LDR, I_STR, I_LDA, I_LDX, I_STX} : D_rB;
	1 : R_NONE;
];
```

`D_dstE` means the value from execute stage will store in which register:

```python
int D_dstE = [
	d_icode in {I_LDA, I_AMR, I_SMR, I_AIR, I_SIR}
	1 : R_NONE;
];
```

`D_dstM` means the value from memory stage will store in which register:

```python
int D_dstM = [
	d_icode in {I_LDR, I_LDX}
];
```

`D_valC`

```python
int D_valC = [
	ea_icode in {I_LDR, I_STR, I_LDA, I_LDX, I_STX} : ea_valEA;
	1: valC;
];
```

#### 3. Execute Stage

We design ALU to calculate two number from `aluA` and `aluB`.

`aluA` definition:

```python
int e_aluA = [
	e_icode in {I_AMR, I_SMR, I_AIR, I_SIR} : valA;
	#
];
```

`aluB` definition:

```python
int e_aluB = [
	e_icode in {I_AMR, I_SMR, I_AIR, I_SIR} : valC;
];
```

#### 4. Memory Stage

The addresses can be divided into two types:

 - Memory addresses are the results of execute stage:

|icode |Instruction|Meaning|
|---|---|---|
|001|LDR|M -> R|
|002|STR|R -> M|
|004|AMR|R+M -> R|
|005|SMR|R-M -> R|

<center>Table 5. Instructions Using Memory Address from Execute Stage</center>

 - Memory addresses are stored in registers:

Read or write memory of the address.

 - Read memory:

|icode|instruction|Meaning|
|---|---|---|
|001|LDR|M -> R|
|004|AMR|R+M -> R|
|005|SMR|R-M -> R|

<center>Table 6. Instructions Reading Memory</center>

- Write memory:

|icode|instruction|Meaning|
|---|---|---|
|002|STR|R -> M|

<center>Table 7. Instructions Writing Memory</center>

#### 5. Write Back

Save `w_valE` to `w_dstE` and `w_valW` to `w_dstW` except instruction which writes data to memory, like `STR`.


#### 6. Pipeline Examples

We give some programs to test the function of our pipeline which contains bubbling, stalling, and forwarding. These programs are adapted from [CSAPP](http://csapp.cs.cmu.edu/3e/courses.html).

### Avoid Data Hazards by Fowarding 

```c
# prog 1
LDA R0, 0, 0, $10 //mov $10 -> R0
LDA R1, 0, 0, $3  //mov $3 -> R1
AND r0, r1        //and r0, r1 -> r0
HALT

//Assembly
//0000110000001010
//0000110100000011
//0100110001000000
//0000000000000000
```

|Cycle|1|2|3|4|5|6|7|8|
|---|---|---|---|---|---|---|---|---|
|Instruction 1|F|D|E|**M**|W|
|Instruction 2||F|D|**E**|M|W|
|Instruction 3|||F|**D**|E|M|W|
|Instruction 4||||**F**|D|E|M|W|







 