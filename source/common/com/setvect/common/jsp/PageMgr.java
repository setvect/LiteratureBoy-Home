package com.setvect.common.jsp;

/**
 * ����¡ ó�� <br>
 * ----�����----&lt; <br>
 * pageMgr = new PageMgr(�� ����Ʈ ����, �������� ǥ�ð���, ������ �ѹ�(1���� N����)) <br>
 * <br>
 * �� JSP�ܿ����� �Ʒ��� ���� ��� <br>
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

	/** ���������� ���� ���ڵ� ���� * */
	private int pageSize;

	/** ���ŵǴ� ������ ���� * */
	private int pageLinkCnt = 10;

	/** ��ü ���ڵ� �� * */
	private int totCnt = 0;

	/** ���� ������ ��ȣ * */
	private int curPage = 0;

	/** ��ü ������ �� * */
	private int totPage = 0;

	/** ��Ÿ�� ó�� ������ ��ȣ * */
	private int startPage = 0;

	/** ��Ÿ�� ������ ������ ��ȣ * */
	private int endPage = 0;

	/** �׷������ ���� ���� * */
	private int groupPageCnt = 0;

	private DesignElement skin = new DesignElement();;

	/***********************************************************************************************
	 * ������ �̵��� ���� �ڹٽ�ũ��Ʈ <br>
	 * (fuction pageMove('', '')������ ��ũ��Ʈ �ҽ�
	 **********************************************************************************************/

	/** ������ submit �� �̸� */
	private String formName = "listPageForm";

	/** �ڹٽ�ũ��Ʈ�� ������� �ʰ� ������ �׺���̼� �ּҸ� ��ũ �ϱ� ���� */
	private URLParameter param;

	/**
	 * ����
	 */
	public PageMgr() {
		init(0, 0, 0);
	}

	/**
	 * ���� �������� ���� ��ȣ�� �������� ������� ���� ���� ���ȣ�� ������<br>
	 * ���� ����
	 * 
	 * @param relative
	 *            ��� ���� ��ȣ
	 * @return ���ȣ
	 */
	public int getRowNum(int relative) {
		return pageSize * (curPage - 1) + 1 + relative;
	}

	/**
	 * ���� �������� ���� ��ȣ�� �������� ������� ���� ���� ���ȣ�� ������<br>
	 * ���� ����
	 * 
	 * @param relative
	 *            ��� ���� ��ȣ
	 * @return ���ȣ
	 */
	public int getRowNumDesc(int relative) {
		return totCnt - (pageSize * (curPage - 1)) - relative;
	}

	/**
	 * ����¡ ó��
	 * 
	 * @param p_totalCount
	 *            ��ü ���ڵ� ��
	 * @param p_curPage
	 *            ���� ������ ��ȣ
	 * @param p_pageSize
	 *            ���������� ǥ�� ��� ����
	 */
	public PageMgr(int p_totalCount, int p_curPage, int p_pageSize) {
		init(p_totalCount, p_curPage, p_pageSize);
	}

	/**
	 * ����¡ ó��
	 * 
	 * @param p_totalCount
	 *            ��ü ���ڵ� ��
	 * @param p_curPage
	 *            ���� ������ ��ȣ
	 * @param p_pageSize
	 *            ���������� ǥ�� ��� ����
	 */
	public void init(int p_totalCount, int p_curPage, int p_pageSize) {

		totCnt = p_totalCount;

		if (totCnt <= 0) {
			totPage = 0;
			curPage = 0;
		}
		pageSize = p_pageSize;
		totPage = (int) Math.ceil((double) totCnt / pageSize);

		// pageLinkCnt ���ŵǴ� ������ ����
		groupPageCnt = (int) Math.ceil((double) totPage / pageLinkCnt);

		curPage = p_curPage;
		curPage = comparePage(curPage);

		startPage = comparePage(((int) Math.ceil((double) curPage / pageLinkCnt) - 1) * pageLinkCnt + 1);
		endPage = comparePage(startPage + pageLinkCnt - 1);

	}

	/**
	 * �ܺο��� pageSize ����
	 * 
	 * @param _pageSize
	 *            �������� �� ���� ����
	 */
	public void setPageSize(int _pageSize) {
		pageSize = _pageSize;
	}

	/**
	 * �ܺο��� PageLinkCnt ����
	 * 
	 * @param _pageLinkCnt
	 *            1 2 3 4 5 6 ó�� ������ ��ȣ�� ������ ����
	 */
	public void setPageLinkCnt(int _pageLinkCnt) {
		pageLinkCnt = _pageLinkCnt;
	}

	/**
	 * pageMove �ڹٽ�ũ��Ʈ �ҽ��� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
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
	 * �� �������� �� row�� ������ ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ���� ��� 1 2 3 4 5 6.. ���� ��������ȣ�� �����Ǵ� ������ ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getPageLinkCnt() {
		return pageLinkCnt;
	}

	/**
	 * ù �������� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * ������ �������� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * ��ü ī��Ʈ�� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getTotCnt() {
		return totCnt;
	}

	/**
	 * ���� ������ ��ũ�� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getTotPage() {
		return totPage;
	}

	/**
	 * ���� �������� ��ȯ�ϴ� �Լ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * ���� ���� Html �ڵ�
	 * 
	 * @param imgStr
	 *            ���� �̹��� html �±�
	 * @return �ش� �ڷ��� ��
	 */
	public String getHrefPrevGroup(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		// ���� ������ �������� �Ѿ� ���� ������
		if ((int) Math.ceil((double) curPage / pageLinkCnt) <= 1) {
			pageLink = skin.getPrevGroup(imgStr);
		}
		// ������
		else {
			int v = ((int) (Math.floor((curPage - 0.0001) / pageLinkCnt))) * pageLinkCnt;
			pageLink = skin.getPrevGroup(skin.getLink(v, imgStr));
		}

		return pageLink;
	}

	/**
	 * ���� ������ Html �ڵ�
	 * 
	 * @param imgStr
	 *            ���� �̹��� html �±�
	 * @return �ش� �ڷ��� ��
	 */
	public String getHrefPrev(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		if (curPage <= 1) {
			pageLink = skin.getPrev(imgStr);
		}
		// ������
		else {
			int v = curPage - 1;
			pageLink = skin.getPrev(skin.getLink(v, imgStr));
		}

		return pageLink;
	}

	/**
	 * ���� ������ html �ڵ�
	 * 
	 * @return �ش� �ڷ��� ��
	 */
	public String getHrefNumber() {
		String pageLink = "";
		for (int i = startPage; i <= endPage; i++) {
			// ���� �������� ������
			if (i == curPage) {
				pageLink += skin.getNumber(skin.getCurrent(String.valueOf(i)));
			} else {
				pageLink += skin.getNumber(skin.getLink(i, String.valueOf(i)));
			}
		}
		return pageLink;
	}

	/**
	 * ���� ������ html �ڵ�
	 * 
	 * @param imgStr
	 *            ���� �̹��� html �±�
	 * @return �ش� �ڷ��� ��
	 */
	public String getHrefNext(String imgStr) {
		imgStr = imgStr.trim();

		String pageLink;
		if (curPage >= totPage) {
			pageLink = skin.getNext(imgStr);
		}
		// ������
		else {
			int v = curPage + 1;
			pageLink = skin.getPrev(skin.getLink(v, imgStr));
		}
		return pageLink;
	}

	/**
	 * ���� ���� Html �ڵ�
	 * 
	 * @param imgStr
	 *            ���� �̹��� html �±�
	 * @return �ش� �ڷ��� ��
	 */
	public String getHrefNextGroup(String imgStr) {
		// ���������� �������� �Ѿ� ���� ������
		String pageLink;
		if ((int) Math.ceil((double) curPage / pageLinkCnt) >= groupPageCnt) {
			pageLink = skin.getNextGroup(imgStr);
		}
		// ���������� �������� �Ѿ� ���� ������
		else {
			int v = (((int) (Math.floor((curPage - 0.001) / pageLinkCnt)) + 1) * pageLinkCnt) + 1;

			pageLink = skin.getNextGroup(skin.getLink(v, imgStr));
		}
		return pageLink;
	}

	/**
	 * ������ó���� ���� ������ ��ũ�� �����ִµ� ���Ǵ� �޼ҵ�<br>
	 * 
	 * @return �������� ������� ���� �⺻ �׺���̼� html �ڵ�
	 */
	public String getPageLink() {

		String pageLink = "";

		if (totCnt <= 0) {
			totPage = 0;
			curPage = 0;
			return "";
		}

		// == ���� ���� ==
		pageLink += getHrefPrevGroup("<font color='#666666'>��I</font>");

		// == ���� ������ ==
		pageLink += getHrefPrev("<font color='#666666'>��</font>");

		// == ���ڷε� ������ ��ũ==
		pageLink += getHrefNumber();

		// == ���� ������ ==
		pageLink += getHrefNext("<font color='#666666'>��</font>");

		// == ���� ���� ==
		pageLink += getHrefNextGroup("<font color='#666666'>I��</font>");

		return getScriptSrc() + pageLink.trim();
	}

	/**
	 * ���� �������� ��ü �������� ���ϴ� �޼ҵ�
	 * 
	 * @param _page
	 *            int ���Ǵ� ������
	 * @return int 1���� ������ 1, ��ü ���������� ũ�� ��ü ��������ȣ
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
	 * ���� ����
	 * 
	 * @param html
	 *            ���� HTML
	 */
	public void setHtmlPrevGroup(String html) {
		skin.prevGroup = html;
	}

	/**
	 * ���� ������
	 * 
	 * @param html
	 *            ���� HTML
	 */
	public void setHtmlPrev(String html) {
		skin.prev = html;
	}

	/**
	 * ���� ������
	 * 
	 * @param html
	 *            ���� HTML
	 */
	public void setHtmlNext(String html) {
		skin.next = html;
	}

	/**
	 * ���� �׷�
	 * 
	 * @param html
	 *            ���� HTML
	 */
	public void setHtmlNextGroup(String html) {
		skin.nextGroup = html;
	}

	/**
	 * ��ȣ ��ũ �յ�
	 * 
	 * @param tag
	 */
	public void setHtmlNumber(String tag) {
		skin.number = tag;
	}

	/**
	 * ��ũ ��ũ �յ�
	 * 
	 * @param antor
	 */
	public void setHtmlLink(String antor) {
		skin.link = antor;
	}

	/**
	 * @param cur
	 *            ���� ������ ǥ�� �±�
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
		// ����� ���׶����� �ٽ� ����
		skin = new DesignElement();
	}

	/**
	 * ������ ���<br>
	 * page navigation�� ��ũ ������ ��ҿ� ����(��ũ ��ȣ, �ּ�)�� ����
	 * 
	 * @author <a href="mailto:setvect@idq.co.kr">����ȣ </a>
	 * @version $Id$
	 */
	public class DesignElement {

		/** �������� ��ġ ������ */
		public final static String HTML_SEPARATOR = "!!";

		/** ������ ���� ��ġ ������ */
		public final static String PAGE_SEPARATOR = "##";

		/** ���� ���� ��ũ �ױ� */
		private String prevGroup;

		/** ���� ������ */
		private String prev;

		/** ���������� */
		private String next;

		/** ���� ���� */
		private String nextGroup;

		/** ������ ���� ǥ�� */
		private String number;

		/** ��ũ ���� */
		private String link;

		/** ���� ������ */
		private String current;

		public DesignElement() {
			// �ʱ� �⺻�� ����
			prevGroup = HTML_SEPARATOR;
			prev = HTML_SEPARATOR;
			next = HTML_SEPARATOR;
			nextGroup = HTML_SEPARATOR;
			number = HTML_SEPARATOR + "&nbsp;&nbsp;";
			URLParameter u = PageMgr.this.getParam();
			// �ĸ����� ������ ���� ��� javascript��
			if (u == null) {
				link = "<a href=\"#\" onkeypress=\"if(event.keyCode == 13){pf_pageMove('" + PAGE_SEPARATOR
						+ "')}; return false;\" onclick=\"pf_pageMove('" + PAGE_SEPARATOR + "'); return false;\">"
						+ HTML_SEPARATOR + "</a>";
			}
			// ���� ��� �������� url �ּ� ���·�
			else {
				u.put("mode", "listForm");
				u.remove("curPage");

				link = "<a href=\"" + u.getParam() + "&amp;curPage=" + PAGE_SEPARATOR + "\">" + HTML_SEPARATOR + "</a>";
			}
			current = "<font color='#FF0000'>" + HTML_SEPARATOR + "</font>";
		}

		/**
		 * ���� ���� ���� Html
		 * 
		 * @param v
		 *            ������
		 * @return ������ �����ؼ� ����
		 */
		public String getPrevGroup(String v) {
			return prevGroup.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * ���� ������ ���� Html
		 * 
		 * @param v
		 *            ������
		 * @return ������ �����ؼ� ����
		 */
		public String getPrev(String v) {
			return prev.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * ���� ������ ���� Html
		 * 
		 * @param v
		 *            ������
		 * @return ������ �����ؼ� ����
		 */
		public String getNext(String v) {
			return next.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * ���� ���� ���� Html
		 * 
		 * @param v
		 *            ������
		 * @return ������ �����ؼ� ����
		 */
		public String getNextGroup(String v) {
			return nextGroup.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * ������ ���� ǥ��
		 * 
		 * @param v
		 *            ������
		 * @return ������ �����ؼ� ����
		 */
		public String getNumber(String v) {
			return number.replaceAll(HTML_SEPARATOR, v);
		}

		/**
		 * ��ũ ���� Html
		 * 
		 * @param page
		 *            ���� ������ ����
		 * @param v
		 *            ���� ��
		 * @return ������ �����ؼ� ����
		 */
		public String getLink(int page, String v) {
			v = link.replaceAll(HTML_SEPARATOR, v);
			return v.replaceAll(PAGE_SEPARATOR, String.valueOf(page));
		}

		/**
		 * ���� �������� ��Ÿ���� Html �±�
		 * 
		 * @return ������ �����ؼ� ����
		 */
		public String getCurrent(String v) {
			return current.replaceAll(HTML_SEPARATOR, v);
		}

	}

}
