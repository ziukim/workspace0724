package com.kh.review.poly;

public class LibraryController {
	private Member mem = null;
	private Book[] bList = new Book[5];
	
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("아기공룡 뿌꾸", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	
	public void insertMember(Member mem) {
		this.mem = mem; //4줄에서 선언한 mem필드에 새로 입력받은 15줄의 mem을 넣겠다
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		//새 배열 생성
		Book[] searchList = new Book[bList.length];
		
		int count = 0;
		//bList에서 제목에 keyword가 포함된 도서 찾기
		for(Book b : bList) {
			if(b != null && b.getTitle().contains(keyword)) {
				searchList[count] = b;
				count++;
			}
		}
		
		return searchList;
	}
	
	public int rentBook(int index) {
		int result = 0;

		Book rentBook = bList[index];
		if(rentBook instanceof AniBook) {
			AniBook b = (AniBook)rentBook; //다운캐스팅
			if(mem.getAge()  < b.getAccessAge()) {
				result = 1;
			}
		} else if(rentBook instanceof CookBook) {
			CookBook b = (CookBook)rentBook;
			if(b.isCoupon()) {
				mem.setCouponCount(mem.getCouponCount() + 1);
				result = 2;
			}
		}
		
		return result;
	}
	
	//도서목록이 가득찼을경우 길이가 +1 배열을 새로 생성하여 추가
	public void insertBook(Book b) {
		for(int i=0; i<bList.length; i++) {
			if(bList[i] == null) {
				bList[i] = b;
				return;
			}
		}
		
		//종료되지않음 -> 모든 bList의 index가 null이 아님
		Book[] newList = new Book[bList.length + 1];
		for(int i=0; i<bList.length; i++) { //bList를 전부 반복하며 새로운 리스트에 복사
			newList[i] = bList[i];
		}
		
		newList[newList.length - 1] = b;
		bList = newList;
	}
	
}



