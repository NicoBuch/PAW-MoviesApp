package ar.edu.itba.it.paw.domain.prize;

import ar.edu.itba.it.paw.domain.NoIdException;

public interface PrizeRepo {
	
	public Prize get(int id) throws NoIdException;
	
	public void delete(Prize prize);
	
	public void save(Prize prize);

}
