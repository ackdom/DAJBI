package cz.cvut.fit.dajbi.instruction;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.instruction.invoke.INVOKESTATIC;
import cz.cvut.fit.dajbi.instruction.invoke.RETURN;
import cz.cvut.fit.dajbi.instruction.load.ALOAD;
import cz.cvut.fit.dajbi.instruction.load.ILOAD;
import cz.cvut.fit.dajbi.instruction.stack.BIPUSH;
import cz.cvut.fit.dajbi.instruction.store.ISTORE;
import cz.cvut.fit.dajbi.instruction.store.PUTSTATIC;
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
		
		// Calls & Returns 	
		case invokestatic:
			return new INVOKESTATIC(f);
		case _return:
			return new RETURN(f);
			
		// Locals
			
		// Store
		case istore:
			return new ISTORE(f);
		case istore_0:
		case istore_1:
		case istore_2:
		case istore_3:
			return new ISTORE(f,instruction.getValue()-Instructions.istore_0.getValue());
			
		case putstatic:
			return new PUTSTATIC(f);
		// Load
		case iload:
			return new ILOAD(f);
		case iload_0:
		case iload_1:
		case iload_2:
		case iload_3:
			return new ILOAD(f,instruction.getValue()-Instructions.iload_0.getValue());
		case aload:
			return new ALOAD(f);
			
			

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