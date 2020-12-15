package reply.model;

import java.util.Date;

public class Reply {
	private int id;// id 클수록 나중에 작성된 댓글
	private String memberid;
	private int articleNum;
	private String body;
	private Date regDate;
	
	public Reply(int id, String memberid, int articleNum, String body, Date regDate) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.articleNum = articleNum;
		this.body = body;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public String getMemberid() {
		return memberid;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public String getBody() {
		return body;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	
}
