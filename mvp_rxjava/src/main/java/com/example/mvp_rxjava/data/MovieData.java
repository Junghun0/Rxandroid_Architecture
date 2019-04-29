package com.example.mvp_rxjava.data;

/*
boxofficeType	문자열	박스오피스 종류를 출력합니다.
showRange	문자열	박스오피스 조회 일자를 출력합니다.
rnum	문자열	순번을 출력합니다.
rank	문자열	해당일자의 박스오피스 순위를 출력합니다.
rankInten	문자열	전일대비 순위의 증감분을 출력합니다.
rankOldAndNew	문자열	랭킹에 신규진입여부를 출력합니다.
“OLD” : 기존 , “NEW” : 신규
movieCd	문자열	영화의 대표코드를 출력합니다.
movieNm	문자열	영화명(국문)을 출력합니다.
openDt	문자열	영화의 개봉일을 출력합니다.
salesAmt	문자열	해당일의 매출액을 출력합니다.
salesShare	문자열	해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
salesInten	문자열	전일 대비 매출액 증감분을 출력합니다.
salesChange	문자열	전일 대비 매출액 증감 비율을 출력합니다.
salesAcc	문자열	누적매출액을 출력합니다.
audiCnt	문자열	해당일의 관객수를 출력합니다.
audiInten	문자열	전일 대비 관객수 증감분을 출력합니다.
audiChange	문자열	전일 대비 관객수 증감 비율을 출력합니다.
audiAcc	문자열	누적관객수를 출력합니다.
scrnCnt	문자열	해당일자에 상영한 스크린수를 출력합니다.
showCnt	문자열	해당일자에 상영된 횟수를 출력합니다.
*/

public class MovieData {

    private String movieNm;
    private String boxofficeType; //박스오피스 종류
    private String showRange; // 박스오피스 조회 일자
    private String rank; // 박스오피스 순위
    private String openDt; // 영화의 개봉일
    private String salesAmt; // 해당일의 매출액
    private String salesAcc; // 누적매출액 출력
    private String audiCnt; // 해당일의 관객수를 출력
    private String audiAcc; // 누적관객수를 출력
    private String audiChange; // 전일 대비 관객수 증감비

    public MovieData(){}

    public MovieData(String movieNm, String boxofficeType, String showRange, String rank, String openDt, String salesAmt, String salesAcc, String audiCnt, String audiAcc, String audiChange) {
        this.movieNm = movieNm;
        this.boxofficeType = boxofficeType;
        this.showRange = showRange;
        this.rank = rank;
        this.openDt = openDt;
        this.salesAmt = salesAmt;
        this.salesAcc = salesAcc;
        this.audiCnt = audiCnt;
        this.audiAcc = audiAcc;
        this.audiChange = audiChange;
    }

    public String getAudiChange() {
        return audiChange;
    }

    public void setAudiChange(String audiChange) {
        this.audiChange = audiChange;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getSalesAmt() {
        return salesAmt;
    }

    public void setSalesAmt(String salesAmt) {
        this.salesAmt = salesAmt;
    }

    public String getSalesAcc() {
        return salesAcc;
    }

    public void setSalesAcc(String salesAcc) {
        this.salesAcc = salesAcc;
    }

    public String getAudiCnt() {
        return audiCnt;
    }

    public void setAudiCnt(String audiCnt) {
        this.audiCnt = audiCnt;
    }

    public String getAudiAcc() {
        return audiAcc;
    }

    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }
}
