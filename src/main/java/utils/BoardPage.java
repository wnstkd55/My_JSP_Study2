package utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
            int pageNum, String reqUrl) {
        String pagingStr = "";

        // �ܰ� 3 : ��ü ������ �� ���
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

        // �ܰ� 4 : '���� ������ ��� �ٷΰ���' ���
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        if (pageTemp != 1) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[ù ������]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                         + "'>[���� ���]</a>";
        }

        // �ܰ� 5 : �� ������ ��ȣ ���
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // ���� �������� ��ũ�� ���� ����
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
                             + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // �ܰ� 6 : '���� ������ ��� �ٷΰ���' ���
        if (pageTemp <= totalPages) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
                         + "'>[���� ���]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
                         + "'>[������ ������]</a>";
        }
        
      //  System.out.println(pagingStr);

        return pagingStr;
    }
}
