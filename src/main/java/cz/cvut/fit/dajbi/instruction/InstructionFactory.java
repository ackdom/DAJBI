package cz.cvut.fit.dajbi.instruction;



import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.instruction.alloc.NEW;
import cz.cvut.fit.dajbi.instruction.conversion.I2B;
import cz.cvut.fit.dajbi.instruction.conversion.I2C;
import cz.cvut.fit.dajbi.instruction.conversion.I2D;
import cz.cvut.fit.dajbi.instruction.conversion.I2F;
import cz.cvut.fit.dajbi.instruction.conversion.I2L;
import cz.cvut.fit.dajbi.instruction.conversion.I2S;
import cz.cvut.fit.dajbi.instruction.conversion.L2D;
import cz.cvut.fit.dajbi.instruction.conversion.L2F;
import cz.cvut.fit.dajbi.instruction.invoke.INVOKESPECIAL;
import cz.cvut.fit.dajbi.instruction.invoke.INVOKESTATIC;
import cz.cvut.fit.dajbi.instruction.invoke.INVOKEVIRTUAL;
import cz.cvut.fit.dajbi.instruction.invoke.RETURN;
import cz.cvut.fit.dajbi.instruction.load.LOAD;
import cz.cvut.fit.dajbi.instruction.math.ADD;
import cz.cvut.fit.dajbi.instruction.math.IINC;
import cz.cvut.fit.dajbi.instruction.math.MUL;
import cz.cvut.fit.dajbi.instruction.math.SUB;
import cz.cvut.fit.dajbi.instruction.stack.BIPUSH;
import cz.cvut.fit.dajbi.instruction.stack.DUP;
import cz.cvut.fit.dajbi.instruction.stack.DUPX1;
import cz.cvut.fit.dajbi.instruction.stack.ICONST;
import cz.cvut.fit.dajbi.instruction.stack.LDC;
import cz.cvut.fit.dajbi.instruction.stack.POP;
import cz.cvut.fit.dajbi.instruction.stack.SIPUSH;
import cz.cvut.fit.dajbi.instruction.store.PUTSTATIC;
import cz.cvut.fit.dajbi.instruction.store.STORE;
import cz.cvut.fit.dajbi.stack.Frame;

public class InstructionFactory {

	public InstructionFactory() {		
	}
	
	
	public static Instruction byCode(int i, Frame f) {
		
		Instructions instruction = Instructions.getInstruction(i);
		
		
		switch (instruction) {
		
		// Stack
		case bipush:
			return new BIPUSH(f);
		case sipush:
			return new SIPUSH(f);
		case iconst_m1:
		case iconst_0:
		case iconst_1:
		case iconst_2:
		case iconst_3:
		case iconst_4:
		case iconst_5:
			return new ICONST(f, instruction.getValue() - Instructions.iconst_0.getValue() );
		case pop:
			return new POP(f);
		case dup:
			return new DUP(f);
		case dup_x1:
			return new DUPX1(f);			
		case ldc:
			return new LDC(f,false);
		case ldc2_w:
		case ldc_w:
			return new LDC(f,true);
		
		// Calls & Returns 	
		case invokestatic:
			return new INVOKESTATIC(f);
		case invokevirtual:
			return new INVOKEVIRTUAL(f);
		case invokespecial:
			return new INVOKESPECIAL(f);
		
		case ireturn:
		case lreturn:
		case dreturn:
		case areturn:
		case freturn:
			return new RETURN(f,true);
		case _return:
		
			return new RETURN(f);
			
			//conversion
		case i2b:
			return new I2B(f);
		case i2c:
			return new I2C(f);
		case i2d:
			return new I2D(f);
		case i2f:
			return new I2F(f);
		case i2l:
			return new I2L(f);
		case i2s:
			return new I2S(f);
			
		case l2d:
			return new L2D(f);
		case l2f:
			return new L2F(f);
		case l2i:
			return new L2I(f);
			
			
		//Alloc 
			
		case _new:
			return new NEW(f);
			
		// Locals
			
			
		// Store
		case istore:
		case dstore:
		case astore:
		case lstore:
		case fstore:
			return new STORE(f);
		case istore_0:
		case istore_1:
		case istore_2:
		case istore_3:
			return new STORE(f,instruction.getValue()-Instructions.istore_0.getValue());
			
		case dstore_0:
		case dstore_1:
		case dstore_2:
		case dstore_3:
			return new STORE(f,instruction.getValue()-Instructions.dstore_0.getValue());

		case fstore_0:
		case fstore_1:
		case fstore_2:
		case fstore_3:
			return new STORE(f,instruction.getValue()-Instructions.fstore_0.getValue());
		
		case lstore_0:
		case lstore_1:
		case lstore_2:
		case lstore_3:
			return new STORE(f,instruction.getValue()-Instructions.lstore_0.getValue());
		case astore_0:
		case astore_1:
		case astore_2:
		case astore_3:
			return new STORE(f,instruction.getValue()-Instructions.astore_0.getValue());

		
		case putstatic:
			return new PUTSTATIC(f);
		// Load
		case iload:
		case aload:
		case fload:
		case lload:
		case dload:
			return new LOAD(f);
		case iload_0:
		case iload_1:
		case iload_2:
		case iload_3:
			return new LOAD(f,instruction.getValue()-Instructions.iload_0.getValue());
		case dload_0:
		case dload_1:
		case dload_2:
		case dload_3:
			return new LOAD(f,instruction.getValue()-Instructions.dload_0.getValue());
				
		case fload_0:
		case fload_1:
		case fload_2:
		case fload_3:
			return new LOAD(f,instruction.getValue()-Instructions.fload_0.getValue());
			
			
		case lload_0:
		case lload_1:
		case lload_2:
		case lload_3:
			return new LOAD(f,instruction.getValue()-Instructions.lload_0.getValue());
			
			
		case aload_0:
		case aload_1:
		case aload_2:
		case aload_3:
			return new LOAD(f,instruction.getValue()-Instructions.aload_0.getValue());
		
			
		// MATH
		// int	
		case isub:
			return new SUB<Integer>(f);
		case iadd:
			return new ADD<Integer>(f);
		case imul:
			return new MUL<Integer>(f);
		case iinc:
			return new IINC(f);
		//double
		case dsub:
			return new SUB<Double>(f);
		case dadd:
			return new ADD<Double>(f);
		case dmul:
			return new MUL<Double>(f);
		//float
		case fsub:
			return new SUB<Float>(f);
		case fadd:
			return new ADD<Float>(f);
		case fmul:
			return new MUL<Float>(f);
		//long
		case lsub:
			return new SUB<Long>(f);
		case ladd:
			return new ADD<Long>(f);
		case lmul:
			return new MUL<Long>(f);
			
			
			
			
			

		default:
			DAJBI.logger.error("cant find instruction: "+i);
			break;
		}
		
		
		
		return null;
	}
	
	

	public enum Instructions {

        aaload(50),
        aastore(83),
        aload(25),
        aload_0(42),
        aload_1(43),
        aload_2(44),
        aload_3(45),
        anewarray(189),
        areturn(176),
        arraylength(190),
        astore(58),
        astore_0(75),
        astore_1(76),
        astore_2(77),
        astore_3(78),
        athrow(191),
        baload(51),
        bastore(84),
        bipush(16),
        caload(52),
        castore(85),
        dadd(99),
        daload(49),
        dastore(82),
        dload(24),
        dload_0(38),
        dload_1(39),
        dload_2(40),
        dload_3(41),
        dmul(107),
        dreturn(175),
        dstore(57),
        dstore_0(71),
        dstore_1(72),
        dstore_2(73),
        dstore_3(74),
        dsub(103),
        dup(89),
        dup_x1(90),       
        fadd(98),
        faload(48),
        fastore(81),
        fload(23),
        fload_0(34),
        fload_1(35),
        fload_2(36),
        fload_3(37),
        fmul(106),
        freturn(174),
        fstore(56),
        fstore_0(67),
        fstore_1(68),
        fstore_2(69),
        fstore_3(70),
        fsub(102),
        getfield(180),
        getstatic(178),
        _goto(167),
        goto_w(200),
        i2b(145),
        i2c(146),
        i2d(135),
        i2f(134),
        i2l(133),
        i2s(147),
        iadd(96),
        iaload(46),
        iastore(79),
        iconst_m1(2),
        iconst_0(3),
        iconst_1(4),
        iconst_2(5),
        iconst_3(6),
        iconst_4(7),
        iconst_5(8),
        if_icmpeq(159),
        if_icmpne(160),
        if_icmplt(161),
        if_icmpge(162),
        if_icmpgt(163),
        if_icmple(164),        
        ifeq(153),
        ifne(154),
        iflt(155),
        ifge(156),
        ifgt(157),
        ifle(158),
        ifnonnull(199),
        ifnull(198),
        iinc(132),
        iload(21),
        iload_0(26),
        iload_1(27),
        iload_2(28),
        iload_3(29),
        imul(104),
        ineg(116),
        _instanceof(193),
        //invokeinterface),
        invokespecial(183),
        invokestatic(184),
        invokevirtual(182),
        ireturn(172),
        istore(54),
        istore_0(59),
        istore_1(60),
        istore_2(61),
        istore_3(62),
        isub(100),
        l2d(138),
        l2f(137),
        l2i(136),
        ladd(97),
        laload(47),
        lastore(80),
       // lcmp),
        //lconst_<l>),
        ldc(18),
        ldc_w(19),
        ldc2_w(20),
      //  ldiv),
        lload(22),
        lload_0(30),
        lload_1(31),
        lload_2(32),
        lload_3(33),
        lmul(105),
       // lneg),
      //  lookupswitch),
      //  lor),
      //  lrem),
        lreturn(173),
      //  lshl),
      //  lshr),
        lstore(55),
        lstore_0(63),
        lstore_1(64),
        lstore_2(65),
        lstore_3(66),
        lsub(101),
      //  lushr),
      //  lxor),
      //  monitorenter),
      //  monitorexit),
      //  multianewarray),
        _new(187),
        newarray(188),
        nop(0),
        pop(87),
        pop2(88),
        putfield(181),
        putstatic(179),
      //  ret),
        _return(177),
        saload(53),
        sastore(86),
        sipush(17),
    //    swap),
    //    tableswitch),
    //    wide),
        print(254),
        
        //custom
        invalid(-1);

		private final int value;

		Instructions(int tag) {
			this.value = tag;
		}

		public int getValue() {
			return this.value;
		}

		public static Instructions getInstruction(int number) {
			for (Instructions tag : Instructions.values()) {
				if (tag.getValue() == number) {
					return tag;
				}
			}
			return invalid;
		}
	}

}