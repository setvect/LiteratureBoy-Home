package com.setvect.common.jsp;

/**
 * 페이징 처리 <br>
 * ----사용방법----&lt; <br>
 * pageMgr = new PageMgr(총 리스트 갯수, 한페이지 표시갯수, 페이지 넘버(1부터 N까지)) <br>
 * <br>
 * ※ JSP단에서는 아래와 같이 사용 <br>
 * &lt;% <br>
 * PageMgr pageArticle = (PageMgr)request.getAttribute(&quot;pageArticle&quot;); <br>
 * %&gt; <br>
 * &lt;form name=&quot;frmPageMove&quot; method=&quot;get&quot;
 * action=&quot;/app/memo/memo.do&quot;&gt; <br>
 * &lt;input type=&quot;hidden&quot; name=&quot;mode&quot;
 * value=&quot;listForm&quot;&gt; <br>
 * &lt;input type=&quot;hidden&quot; name=&quot;pageNo&quot;
 * value=&quot;&quot;&gt; <br>
 * &lt;%=pageArticle.getPageLink()%&gt; <br>
 * &lt;/form&gt; <br>
 * 
 * @version $Id: PageMgr.java,v 1.9 2005/06/20 09:25:46 setvect Exp $
 */
public class PageMgr {

	/** 한페이지에 찍힐 레코드 개수 * */
	private int pageSize;

	/** 열거되는 페이지 개수 * */
	private int pageLinkCnt = 10;

	/** 전체 레코드 수 * */
	private int totCnt = 0;

	/** 현재 페이지 번호 * */
	private int curPage = 0;

	/** 전체 페이지 수 * */
	private int totPage = 0;

	/** 나타날 처음 페이지 번호 * */
	private int startPage = 0;

	/** 나타날 마지막 페이지 번호 * */
	private int endPage = 0;

	/** 그룹단위의 묶음 개수 * */
	private int groupPageCnt = 0;

	private DesignElement skin = new DesignElement();;

	/***********************************************************************************************
	 * 페이지 이동에 관한 자바스크립트 <br>
	 * (fuction pageMove('', '')형태의 스크립트 소스
	 **********************************************************************************************/

	/** 페이지 submit 폼 이름 */
	private String formName = "listPageForm";

	/** 자바스크립트를 사용하지 않고 페이지 네비게이션 주소를 링크 하기 위함 */
	private URLParameter param;

	/**
	 * 생성
	 */
	public PageMgr() {
		init(0, 0, 0);
	}

	/**
	 * 현재 페이지에 시작 번호를 기준으로 상대적인 값를 더해 행번호를 가져옴<br>
	 * 오름 차순
	 * 
	 * @param relative
	 *            상대 행의 번호
	 * @return 행번호
	 */
	public int getRowNum(int relative) {
		return pageSize * (curPage - 1) + 1 + relative;
	}

	/**
	 * 현재 페이지에 시작 번호를 기준으로 상대적인 값를 더해 행번호를 가져옴<br>
	 * 내림 차순
	 * 
	 * @param relative
	 *            상대 행의 번호
	 * @return 행번호
	 */
	public int getRowNumDesc(int relative) {
		return totCnt - (pageSize * (curPage - 1)) - relative;
	}

	/**
	 * 페이징 처리
	 * 
	 * @param p_totalCount
	 *            전체 레코드 수
	 * @param p_curPage
	 *            현재 페이지 번호
	 * @param p_pageSize
	 *            한페이지당 표시 목록 갯수
	 */
	public PageMgr(int p_totalCount, int p_curPage, int p_pageSize) {
		init(p_totalCount, p_curPage, p_pageSize);
	}

	/**
	 * 페이징 처리
	 * 
	 * @param p_totalCount
	 *            전체 레코드 수
	 * @param p_curPage
	 *            현제 페이지 번호
	 * @param p_pageSize
	 *            한페이지당 표시 목록 갯수
	 */
	public void init(int p_totalCount, int p_curPage, int p_pageSize) {

		totCnt = p_totalCount;

		if (totCnt <= 0) {
			totPage = 0;
			curPage = 0;
		}
		pageSize = p_pageSize;
		totPage = (int) Math.ceil((double) totCnt / pageSize);

		// pageLinkCnt 열거되는 페이지 갯수
		groupPageCnt = (int) Math.ceil((double) totPage / pageLinkCnt);

		curPage = p_curPage;
		curPage = comparePage(curPage);

		startPage = comparePage(((int) Math.ceil((double) curPage / pageLinkCnt) - 1) * pageLinkCnt + 1);
		endPage = comparePage(startPage + pageLinkCnt - 1);

	}

	/**
	 * 외부에서 pageSize 세팅
	 * 
	 * @param _pageSize
	 *            페이지에 들어갈 행을 세팅
	 */
	public void setPageSize(int _pageSize) {
		pageSize = _pageSize;
	}

	/**
	 * 외부에서 PageLinkCnt 세팅
	 * 
	 * @param _pageLinkCnt
	 *            1 2 3 4 5 6 처럼 페이지 번호로 나열될 갯수
	 */
	public void setPageLinkCnt(int _pageLinkCnt) {
		pageLinkCnt = _pageLinkCnt;
	}

	/**
	 * pageMove 자바스크립트 소스를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public String getScriptSrc() {
		String tmpLink = "<script language=\"javascript\" text=\"text/javascript\">\n";
		tmpLink += " function pf_pageMove(_curPage)\n";
		tmpLink += " {\n";
		tmpLink += "   " + formName + ".curPage.value     = _curPage;\n";
		tmpLink += "   " + formName + ".submit();\n";
		tmpLink += " }\n";
		tmpLink += "</script>\n";

		return tmpLink;
	}

	/**
	 * 한 페이지에 들어갈 row의 갯수를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 예를 들어 1 2 3 4 5 6.. 같이 페이지번호가 나열되는 갯수를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getPageLinkCnt() {
		return pageLinkCnt;
	}

	/**
	 * 첫 페이지를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * 마지막 페이지를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * 전체 카운트를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getTotCnt() {
		return totCnt;
	}

	/**
	 * 위쪽 페이지 링크를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getTotPage() {
		return totPage;
	}

	/**
	 * 현재 페이지를 반환하는 함수
	 * 
	 * @return 해당 자료의 값
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * 이전 묶음 Html 코드
	 * 
	 * @param imgStr
	 *            구할 이미지 html 태그
	 * @return 해당 자료의 값
	 */
	public String getHrefPrevGroup(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		// 이전 페이지 묶음으로 넘어 갈수 없으면
		if ((int) Math.ceil((double) curPage / pageLinkCnt) <= 1) {
			pageLink = skin.getPrevGroup(imgStr);
		}
		// 있으면
		else {
			int v = ((int) (Math.floor((curPage - 0.0001) / pageLinkCnt))) * pageLinkCnt;
			pageLink = skin.getPrevGroup(skin.getLink(v, imgStr));
		}

		return pageLink;
	}

	/**
	 * 다음 페이지 Html 코드
	 * 
	 * @param imgStr
	 *            구할 이미지 html 태그
	 * @return 해당 자료의 값
	 */
	public String getHrefPrev(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		if (curPage <= 1) {
			pageLink = skin.getPrev(imgStr);
		}
		// 있으면
		else {
			int v = curPage - 1;
			pageLink = skin.getPrev(skin.getLink(v, imgStr));
		}

		return pageLink;
	}

	/**
	 * 숫자 페이지 html 코드
	 * 
	 * @return 해당 자료의 값
	 */
	public String getHrefNumber() {
		String pageLink = "";
		for (int i = startPage; i <= endPage; i++) {
			// 현재 페이지와 같으면
			if (i == curPage) {
				pageLink += skin.getNumber(skin.getCurrent(String.valueOf(i)));
			} else {
				pageLink += skin.getNumber(skin.getLink(i, String.valueOf(i)));
			}
		}
		return pageLink;
	}

	/**
	 * 다음 페이지 html 코드
	 * 
	 * @param imgStr
	 *            구할 이미지 html 태그
	 * @return 해당 자료의 값
	 */
	public String getHrefNext(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		if (curPage >= totPage) {
			pageLink = skin.getNext(imgStr);
		}
		// 있으면
		else {
			int v = curPage + 1;
			pageLink = skin.getPrev(skin.getLink(v, imgStr));
		}
		return pageLink;
	}

	/**
	 * 이후 묶음 Html 코드
	 * 
	 * @param imgStr
	 *            구할 이미지 html 태그
	 * @return 해당 자료의 값
	 */
	public String getHrefNextGroup(String imgStr) {
		// 다음페이지 묶음으로 넘어 갈수 없으면
		String pageLink;
		if ((int) Math.ceil((double) curPage / pageLinkCnt) >= groupPageCnt) {
			pageLink = skin.getNextGroup(imgStr);
		}
		// 다음페이지 묶음으로 넘어 갈수 있으면
		else {
			int v = (((int) (Math.floor((curPage - 0.001) / pageLinkCnt)) + 1) * pageLinkCnt) + 1;

			pageLink = skin.getNextGroup(skin.getLink(v, imgStr));
		}
		return pageLink;
	}

	/**
	 * 페이지처리시 메인 페이지 링크를 구해주는데 사용되는 메소드<br>
	 * 
	 * @return 디자인이 적용되지 않은 기본 네비게이션 html 코드
	 */
	public String getPageLink() {

		String pageLink = "";

		if (totCnt <= 0) {
			totPage = 0;
			curPage = 0;
			return "";
		}

		// == 이전 묶음 ==
		pageLink += getHrefPrevGroup("<font color='#666666'>◀I</font>");

		// == 이전 페이지 ==
		pageLink += getHrefPrev("<font color='#666666'>◀</font>");

		// == 숫자로된 페이지 링크==
		pageLink += getHrefNumber();

		// == 다음 페이지 ==
		pageLink += getHrefNext("<font color='#666666'>▶</font>");

		// == 다음 묶음 ==
		pageLink += getHrefNextGroup("<font color='#666666'>I▶</font>");

		return getScriptSrc() + pageLink.trim();
	}

	/**
	 * 현재 페이지를 전체 페이지와 비교하는 메소드
	 * 
	 * @param _page
	 *            int 사용되는 페이지
	 * @return int 1보다 작으면 1, 전체 페이지보다 크면 전체 페이지번호
	 */
	public int comparePage(int _page) {
		int tmp = _page;

		if (_page < 1)
			tmp = 1;
		else if (_page > totPage) {
			tmp = totPage;
		}
		return tmp;
	}

	/**
	 * @param formName
	 *            The formName to set.
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * 이전 묶음
	 * 
	 * @param html
	 *            관련 HTML
	 */
	public void setHtmlPrevGroup(String html) {
		skin.prevGroup = html;
	}

	/**
	 * 이전 페이지
	 * 
	 * @param html
	 *            관련 HTML
	 */
	public void setHtmlPrev(String html) {
		skin.prev = html;
	}

	/**
	 * 다음 페이지
	 * 
	 * @param html
	 *            관련 HTML
	 */
	public void setHtmlNext(String html) {
		skin.next = html;
	}

	/**
	 * 다음 그룹
	 * 
	 * @param html
	 *            관련 HTML
	 */
	public void setHtmlNextGroup(String html) {
		skin.nextGroup = html;
	}

	/**
	 * 번호 링크 앞뒤
	 * 
	 * @param tag
	 */
	public void setHtmlNumber(String tag) {
		skin.number = tag;
	}

	/**
	 * 링크 태크 앞뒤
	 * 
	 * @param antor
	 */
	public void setHtmlLink(String antor) {
		skin.link = antor;
	}

	/**
	 * @param cur
	 *            현재 페이지 표현 태그
	 */
	public void setHtmlCurrent(String cur) {
		skin.current = cur;
	}

	/**
	 * @return Returns the param.
	 */
	public URLParameter getParam() {
		return param;
	}

	/**
	 * @param param
	 *            The param to set.
	 */
	public void setParam(URLParameter param) {
		this.param = param;
		// 변경된 사항때문에 다시 생성
		skin = new DesignElement();
	}

	/**
	 * 디자인 요소<br>
	 * page navigation에 링크 디자인 요소와 변수(링크 번호, 주소)를 따로
	 * 
	 * @author <a href="mailto:setvect@idq.co.kr">장정호 </a>
	 * @version $Id$
	 */
	public class DesignElement {

		/** 변수값에 위치 구분자 */
		public final static String HTML_SEPARATOR = "!!";

		/** 페이지 정보 위치 구분자 */
		public final static String PAGE_SEPARATOR = "##";

		/** 이전 묶음 링크 테그 */
		private String prevGroup;

		/** 이전 페이지 */
		private String prev;

		/** 다음페이지 */
		private String next;

		/** 다음 묶음 */
		private String nextGroup;

		/** 페이지 숫자 표시 */
		private String number;

		/** 링크 영역 */
		private String link;

		/** 현재 페이지 */
		private String current;

		public DesignElement() {
			// 초기 기본값 대입
			prevGroup = HTML_SEPARATOR;
			prev = HTML_SEPARATOR;
			next = HTML_SEPARATOR;
			nextGroup = HTML_SEPARATOR;
			number = HTML_SEPARATOR + "&nbsp;&nbsp;";
			URLParameter u = PageMgr.this.getParam();
			// 파리머터 정보가 없을 경우 javascript로
			if (u == null) {
				link = "<a href=\"#\" onkeypress=\"if(event.keyCode == 13){pf_pageMove('" + PAGE_SEPARATOR
						+ "')}; return false;\" onclick=\"pf_pageMove('" + PAGE_SEPARATOR + "'); return false;\">"
						+ HTML_SEPARATOR + "</a>";
			}
			// 있을 경우 직접적인 url 주소 형태로
			else {
				u.put("mode", "listForm");
				u.remove("curPage");

				link = "<a href=\"" + u.getParam() + "&amp;curPage=" + PAGE_SEPARATOR + "\">" + HTML_SEPARATOR + "</a>";
			}
			current = "<font color='#FF0000'>" + HTML_SEPARATOR + "</font>";
		}

		/**
		 * 이전 묶음 정보 Html
		 * 
		 * @param v
		 *            변수값
		 * @return 변수값 조합해서 리턴
		 */
		public String getPrevGroup(String v) {
			return prevGroup.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * 이전 페이지 정보 Html
		 * 
		 * @param v
		 *            변수값
		 * @return 변수값 조합해서 리턴
		 */
		public String getPrev(String v) {
			return prev.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * 다음 페이지 정보 Html
		 * 
		 * @param v
		 *            변수값
		 * @return 변수값 조합해서 리턴
		 */
		public String getNext(String v) {
			return next.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * 이전 묶음 정보 Html
		 * 
		 * @param v
		 *            변수값
		 * @return 변수값 조합해서 리턴
		 */
		public String getNextGroup(String v) {
			return nextGroup.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * 페이지 숫자 표시
		 * 
		 * @param v
		 *            변수값
		 * @return 변수값 조합해서 리턴
		 */
		public String getNumber(String v) {
			return number.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * 링크 정보 Html
		 * 
		 * @param page
		 *            현재 페이지 정보
		 * @param v
		 *            변수 값
		 * @return 변수값 조합해서 리턴
		 */
		public String getLink(int page, String v) {
			v = link.replaceAll(HTML_SEPARATOR, v);
			return v.replaceAll(PAGE_SEPARATOR, String.valueOf(page));
		}

		/**
		 * 현재 페이지를 나타내는 Html 태그
		 * 
		 * @return 변수값 조합해서 리턴
		 */
		public String getCurrent(String v) {
			return current.replaceAll(HTML_SEPARATOR, v);
		}

	}

}
