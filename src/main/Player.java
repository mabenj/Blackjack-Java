package main;

import java.util.ArrayList;

public class Player {
	private final String nickname;
	
	private boolean hasBlackjack;
	private boolean isBust;
	private boolean readyToPlay;
	
	private ArrayList<Card> hand;
	private int bet;
	protected int balance;
	
	public Player(String nickname) {
		if(nickname.equals("")) {
			nickname = "Jari Avanto";
		}
		
		hasBlackjack = false;
		isBust = false;
		readyToPlay = true;
		
		this.nickname = nickname;
		hand = new ArrayList<Card>();
		bet = 0;
		balance = 10000;
	}
	
	public String getName() {
		return nickname;
	}
	
	public int getBet() {
		return bet;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public boolean hasAce() {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).isAce()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasBlackjack() {
		if(hand.size() == 2 && hasAce() && getHandSum()+10 == 21) {
			hasBlackjack = true;
			return true;
		}
		return false;
	}
	
	public boolean isBust() {
		return isBust;
	}
	
	public boolean isReadyToPlay() {
		return readyToPlay;
	}
	
	public void setHasBlackjack(boolean blackjack) {
		this.hasBlackjack = blackjack;
	}
	
	public void setIsBust(boolean bust) {
		this.isBust = bust;
	}
	
	public void setReadyToPlay(boolean ready) {
		this.readyToPlay = ready;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	//palauttaa koko k‰den
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	//lis‰‰ kortin k‰teen
	public void addToHand(Card card){
		hand.add(card);
	}
	
	//palauttaa k‰den summan (‰ss‰ on 1)
	public int getHandSum() {
		int aceCount = 0;
		int sum = 0;
		
		for(Card card : hand) {
			sum += card.getRank();
			if(card.isAce()) {
				aceCount++;
			}
		}
		/**
		if(sum <= 11 && aceCount > 0) {
			sum += 10;
		}*/
		
		return sum;
	}
	
	/**
	public int getHandSum() {
		int aceCount = 0;
        int sum = 0;

        for (Card card: hand) {
        	if(card.isAce()) {
        		aceCount++;
        	}       	
        	
            sum += card.getRank();
        }

        int decreasedAceValue = 0;

        while (sum > 21 && aceCount > 0 && aceCount != decreasedAceValue)
        {
            sum -= 10;
            decreasedAceValue++;
        }

        return sum;
    }*/
	
	/**
	//palauttaa k‰den arvo(t)n
	public Integer[] getHandSum() {
		Integer[] values = {0, 0};
		int aceCount = 0;
		int sumWithoutAces = 0;
		int sumWithAces = 0;
		
		//lasketaan summa ilman ‰ssi‰
		for(int i = 0; i < hand.size(); i++) {
			
			if(hand.get(i).isAce()) {
				aceCount++;
			} else {
				sumWithoutAces += hand.get(i).getRank();
			}
		}
		
		//ei ‰ssi‰ k‰dess‰
		if(aceCount == 0) {
			values[0] = sumWithoutAces;
			return values;
		}
		
		//yli vaikka kaikki ‰ss‰t ykkˆsi‰
		if(sumWithoutAces + aceCount > 21) {
			values[0] = sumWithoutAces + aceCount;
			return values;
		}
		
		sumWithAces = sumWithoutAces;
		
		//lasketaan summa ‰ssien kanssa
		int i = 1;
		int iterations = aceCount;
		while(i <= iterations && sumWithAces + aceCount <= 10) {
			sumWithAces += 11;
			aceCount--;
		}
		
		values[0] = sumWithoutAces + 
		values[1] = sumWithAces + aceCount;
				
		
		
		return null;
	}*/
}
