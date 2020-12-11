package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {// 한 페이지당 5쪽씩 보이게
	private int total;// 전체 게시물(레코드) 수
	private int currentPage;//8
	private List<Article> content;
	private int totalPages;// 18
	private int startPage;//6
	private int endPage;//10
	
	public ArticlePage(int total, int currentPage, int size, List<Article> content) {// size : 한 페이지에 보이는 게시물 수
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		if(total != 0) {
			this.totalPages = total / size;
			if(total % size > 0) {
				this.totalPages++;
			}
			//this.startPage = currentPage % 5 == 0? currentPage / 5 : currentPage / 5 + 1;
			this.startPage = (currentPage -1) / 5 * 5 + 1;// 18%5 != 0 -> 18/5 = 3 아니면 +1
			this.endPage = Math.min(startPage + 4, totalPages); 
			
			
		}
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean hasNoArticles() {
		return total == 0;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
	
	
	
}
