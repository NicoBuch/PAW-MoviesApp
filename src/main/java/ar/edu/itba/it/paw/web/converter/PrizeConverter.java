package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.prize.Prize;
import ar.edu.itba.it.paw.domain.prize.PrizeRepo;
@Component
public class PrizeConverter implements Converter<String,Prize>{
	private PrizeRepo  prices;
	@Autowired
	public PrizeConverter(PrizeRepo prices){
		this.prices = prices;
	}
	public Prize convert(String source) {
		try {
			return (prices.get(Integer.valueOf(source)));
		} catch (NoIdException e) {
			throw new RuntimeException();
		}
	}

}