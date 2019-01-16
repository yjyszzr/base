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
	 * 根据派奖奖金计算奖金金额
	 * @param result
	 * @param prizeA
	 * @param prizeB
	 * @param prizeC
	 * @param isAppend
	 * @return
	 */
	public static final BigDecimal calculate(LottoResultEntity result,BigDecimal prizeA,BigDecimal prizeB,BigDecimal prizeC,boolean isAppend) {
		BigDecimal r = null;
		if(!result.isCompund) {	//单式计算奖金
			int level = result.lottoLevel.level;
			if(level == 1) {
				r = prizeA;
			}else if(level == 2) {
				r = prizeB;
			}else if(level == 3) {
				r = prizeC;
			}else if(level == 4) {
				r = BigDecimal.valueOf(200);
			}else if(level == 5) {
				r = BigDecimal.valueOf(10);
			}else if(level == 6) {
				r = BigDecimal.valueOf(5);
			}
			if(isAppend) {
				if(level == 1 || level == 2 || level == 3) {
					r = r.multiply(BigDecimal.valueOf(1.6));
				}else if(level == 4 || level == 5) {
					r = r.multiply(BigDecimal.valueOf(1.5));
				}
			}
		}else {	//复试计算奖金算法
			
		}
		return r;
	}
}
