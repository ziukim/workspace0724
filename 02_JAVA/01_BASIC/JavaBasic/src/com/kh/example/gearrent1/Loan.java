package com.kh.example.gearrent1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {
	private String itemId;
	private String memberId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;

	public Loan(String itemId, String memberId, LocalDate loanDate, LocalDate dueDate) {
		super();
		this.itemId = itemId;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}
	
	public boolean isOverdue(LocalDate today) {
		 LocalDate base = (returnedDate != null) ? returnedDate
                 : Objects.requireNonNull(today, "today must not be null");
		 return base.isAfter(dueDate); 
	}

		public int overdueDays(LocalDate today) {
		LocalDate base = (returnedDate != null) ? returnedDate
		                 : Objects.requireNonNull(today, "today must not be null");
		if (!base.isAfter(dueDate)) return 0; 
		return (int) ChronoUnit.DAYS.between(dueDate, base); 
	}

	@Override
	public String toString() {
		return "Loan [itemId=" + itemId + ", memberId=" + memberId + ", loanDate=" + loanDate + ", dueDate=" + dueDate
				+ ", returnedDate=" + returnedDate + "]";
	}
	
	
}
