package notice.model.vo;

public class NoticeComment {

	private int ncNo;
	private int ncLevel;
	private int ncRef;
	private int noticeRef;
	private String ncWriter;
	private String ncContent;
	private String ncDate;
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeComment(int ncNo, int ncLevel, int ncRef, int noticeRef, String ncWriter, String ncContent,
			String ncDate) {
		super();
		this.ncNo = ncNo;
		this.ncLevel = ncLevel;
		this.ncRef = ncRef;
		this.noticeRef = noticeRef;
		this.ncWriter = ncWriter;
		this.ncContent = ncContent;
		this.ncDate = ncDate;
	}
	public int getNcNo() {
		return ncNo;
	}
	public void setNcNo(int ncNo) {
		this.ncNo = ncNo;
	}
	public int getNcLevel() {
		return ncLevel;
	}
	public void setNcLevel(int ncLevel) {
		this.ncLevel = ncLevel;
	}
	public int getNcRef() {
		return ncRef;
	}
	public void setNcRef(int ncRef) {
		this.ncRef = ncRef;
	}
	public int getNoticeRef() {
		return noticeRef;
	}
	public void setNoticeRef(int noticeRef) {
		this.noticeRef = noticeRef;
	}
	public String getNcWriter() {
		return ncWriter;
	}
	public void setNcWriter(String ncWriter) {
		this.ncWriter = ncWriter;
	}
	public String getNcContent() {
		return ncContent;
	}
	public String getNcContentBr() {
		return ncContent.replaceAll("\r\n", "<br>"); 
	}
	public void setNcContent(String ncComment) {
		this.ncContent = ncComment;
	}
	public String getNcDate() {
		return ncDate;
	}
	public void setNcDate(String ncDate) {
		this.ncDate = ncDate;
	}

}
