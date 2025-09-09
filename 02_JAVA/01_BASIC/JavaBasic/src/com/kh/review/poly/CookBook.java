package com.kh.review.poly;

public class CookBook extends Book{
	public boolean coupon;

	public CookBook() {
		super();
	}

	public CookBook(String title, String author, String publisher, boolean coupon) {
		super(title, author, publisher);
		this.coupon = coupon;
	}

	public boolean isCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}
	
	@Override
	public String toString() {
		return "CookBook [Book " + super.toString() + ", coupon = " + coupon + "]";
	}
}
