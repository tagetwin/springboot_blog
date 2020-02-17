package com.yndg.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yndg.blog.repository.PostRepository;

@SpringBootTest
class SpringbootBlogApplicationTests {

	@Autowired
	PostRepository postRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void pagingTest() {


//		int totalCount =  postRepository.getBoardListCnt();
		// 현재 페이지
		int page = 4;
		System.out.println("요청 페이지 : " + page);
		// 총 페이지 수		
		int totalCount = 25;
		// 한 페이지에 출력될 게시물의 수
		int countList = 10;
		
		// 페이지 수 구하기
		int totalPage = totalCount / countList;
		
		// 페이지 수 구하기 보정
		if(totalCount % countList > 0) {
			
			totalPage++;
		}
		
		if(totalPage < page) {
			page = totalPage;
		}
		
		System.out.println("현재 페이지 : " + page);
		System.out.println("총 글의 수 : " + totalCount);
		System.out.println("필요한 페이지수 : " + totalPage);
		
		// 현재 페이지 번호
		page = 22;
		// 화면에 보여줄 페이지 수
		int countPage = 10;
		totalCount = 255;
//		int startPage = ((5 -1) / 10) * 10 +1;
		
		// 페이지 수 구하기
		totalPage = totalCount / countList;

		// 페이지 수 구하기 보정
		if (totalCount % countList > 0) {

			totalPage++;
		}

		if (totalPage < page) {
			page = totalPage;
		}
		
		// 페이지 시작 번호
		int startPage = ((page -1) / countPage) * countPage + 1;
		System.out.println("시작페이지 : " + startPage);
		
		
		// 페이지 마지막 번호
		int endPage = startPage + countPage -1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		System.out.println("끝페이지 : " + endPage);
		
	}
	
}

