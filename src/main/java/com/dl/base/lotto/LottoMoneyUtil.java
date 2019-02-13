package com.dl.base.lotto;

import java.math.BigDecimal;

import com.dl.base.lotto.entity.LottoResultEntity;

/**
 * 乐透计算价钱
 * @author 
 * @date 2018.01.16
 */
public class LottoMoneyUtil {
	
	/**
	 * 根据派奖奖金计算奖金金额V2版
	 * @param result
	 * @param prizeA
	 * @param prizeB
	 * @param prizeC
	 * @param isAppend
	 * @return
	 */
	public static final BigDecimal calculateV2(LottoResultEntity result,BigDecimal prizeA,BigDecimal prizeB,BigDecimal prizeC,BigDecimal prizeAAppend,BigDecimal prizeBAppend,BigDecimal prizeCAppend,boolean isAppend) {
		BigDecimal r = BigDecimal.ZERO;
		if(!result.isCompund) {	//单式计算奖金
			System.out.println("单式计算奖金");
			int level = result.lottoLevel.level;
			if(level == 1) {
				r = prizeA;
			}else if(level == 2) {
				r = prizeB;
			}else if(level == 3) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_THIRD);;
			}else if(level == 4) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_FORTH);
			}else if(level == 5) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_FIFTH);
			}else if(level == 6) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_SIXTH);
			}else if(level == 7) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_SEVENTH);
			}else if(level == 8) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_EIGHTH);
			}else if(level == 9) {
				r = BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_NINTH);
			}
			if(isAppend) {
				if(level == 1 || level == 2) {
					if(level == 1) {
						r = r.add(prizeAAppend);
					}else if(level == 2) {
						r = r.add(prizeBAppend);
					}
				}
			}
		}else {	//复试计算奖金算法
			System.out.println("复式计算奖金");
			int levelACnt = result.lottoLevel.cLevelSuperCount;
			int levelBCnt = result.lottoLevel.cLevelMidCount;
			int levelCCnt = result.lottoLevel.cLevelThirdCount;
			int levelDCnt = result.lottoLevel.cLevelForthCount;
			int levelECnt = result.lottoLevel.cLevelFifthCount;
			int levelFCnt = result.lottoLevel.cLevelSixthCount;
			int levelGCnt = result.lottoLevel.cLevelSevenCount;
			int levelHCnt = result.lottoLevel.cLevelEightCount;
			int levelICnt = result.lottoLevel.cLevelNineCount;
			float factor = 1;
			if(levelACnt > 0) {
				r = r.add(prizeA.multiply(BigDecimal.valueOf(levelACnt*factor)));
			}
			if(levelBCnt > 0) {
				r = r.add(prizeB.multiply(BigDecimal.valueOf(levelBCnt*factor)));
			}
			if(levelCCnt > 0) {
				r = r.add(prizeC.multiply(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_THIRD*factor)));
			}
			if(levelDCnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_FORTH * levelDCnt * factor));
			}
			if(levelECnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_FIFTH * levelECnt * factor));
			}
			if(levelFCnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_SIXTH * levelFCnt));
			}
			if(levelGCnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_SEVENTH * levelFCnt));
			}
			if(levelHCnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_EIGHTH * levelHCnt));
			}
			if(levelICnt > 0) {
				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTOV2_PRIZE_NINTH * levelICnt));
			}
		}
		return r;
	}
	
	
//	/**
//	 * 根据派奖奖金计算奖金金额
//	 * @param result
//	 * @param prizeA
//	 * @param prizeB
//	 * @param prizeC
//	 * @param isAppend
//	 * @return
//	 */
//	public static final BigDecimal calculate(LottoResultEntity result,BigDecimal prizeA,BigDecimal prizeB,BigDecimal prizeC,BigDecimal prizeAAppend,BigDecimal prizeBAppend,BigDecimal prizeCAppend,boolean isAppend) {
//		BigDecimal r = BigDecimal.ZERO;
//		if(!result.isCompund) {	//单式计算奖金
//			System.out.println("单式计算奖金");
//			int level = result.lottoLevel.level;
//			if(level == 1) {
//				r = prizeA;
//			}else if(level == 2) {
//				r = prizeB;
//			}else if(level == 3) {
//				r = prizeC;
//			}else if(level == 4) {
//				r = BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_FORTH);
//			}else if(level == 5) {
//				r = BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_FIFTH);
//			}else if(level == 6) {
//				r = BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_SIXTH);
//			}
//			if(isAppend) {
//				if(level == 1 || level == 2 || level == 3) {
////					r = r.multiply(BigDecimal.valueOf(1.6));
//					if(level == 1) {
//						r = r.add(prizeAAppend);
//					}else if(level == 2) {
//						r = r.add(prizeBAppend);
//					}else if(level == 3) {
//						r = r.add(prizeCAppend);
//					}
//				}else if(level == 4 || level == 5) {
//					r = r.multiply(BigDecimal.valueOf(1.5));
//				}
//			}
//		}else {	//复试计算奖金算法
//			System.out.println("复式计算奖金");
//			int levelACnt = result.lottoLevel.cLevelSuperCount;
//			int levelBCnt = result.lottoLevel.cLevelMidCount;
//			int levelCCnt = result.lottoLevel.cLevelThirdCount;
//			int levelDCnt = result.lottoLevel.cLevelForthCount;
//			int levelECnt = result.lottoLevel.cLevelFifthCount;
//			int levelFCnt = result.lottoLevel.cLevelSixthCount;
//			float factor = 1;
//			if(levelACnt > 0) {
//				if(isAppend) {
//					factor = 1.6f;
//				}
//				r = r.add(prizeA.multiply(BigDecimal.valueOf(levelACnt*factor)));
//			}
//			if(levelBCnt > 0) {
//				if(isAppend) {
//					factor = 1.6f;
//				}
//				r = r.add(prizeB.multiply(BigDecimal.valueOf(levelBCnt*factor)));
//			}
//			if(levelCCnt > 0) {
//				if(isAppend) {
//					factor = 1.6f;
//				}
//				r = r.add(prizeC.multiply(BigDecimal.valueOf(levelCCnt*factor)));
//			}
//			if(levelDCnt > 0) {
//				if(isAppend) {
//					factor = 1.5f;
//				}
//				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_FORTH * levelDCnt * factor));
//			}
//			if(levelECnt > 0) {
//				if(isAppend) {
//					factor = 1.5f;
//				}
//				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_FIFTH * levelECnt * factor));
//			}
//			if(levelFCnt > 0) {
//				r = r.add(BigDecimal.valueOf(LottoCommon.LOTTO_PRIZE_SIXTH * levelFCnt));
//			}
//		}
//		return r;
//	}
}
