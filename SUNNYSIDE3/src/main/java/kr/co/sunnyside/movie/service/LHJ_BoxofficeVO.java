package kr.co.sunnyside.movie.service;

import kr.co.sunnyside.cmn.DTO;

public class LHJ_BoxofficeVO extends DTO {
    /**영화ID*/     
	private String movieId    ;
	/**한글제목*/     
	private String kortitle    ;
	/**영어제목*/     
	private String engtitle    ;
	/**관람가능연령*/   
	private int limitage    ;
	/**장르*/       
	private String genre       ;
	/**개봉일*/      
	private String relDate    ;
	/**감독*/       
	private String director    ;
	/**출연진*/      
	private String cast        ;
	/**러닝타임 */    
	private int runningTime;
	/**줄거리*/      
	private String synopsis    ;
	/**예고편*/      
	private String trailer     ;
	/**타입*/       
	private String filmType   ;
	/**포스터*/      
	private String poster      ;
	/**전문가 평점*/   
	private double expertRate ;
	/**관람객 평점*/   
	private double visitorRate;
	
	public LHJ_BoxofficeVO() {}

	public LHJ_BoxofficeVO(String movieId, String kortitle, String engtitle, int limitage, String genre, String relDate,
			String director, String cast, int runningTime, String synopsis, String trailer, String filmType,
			String poster, double expertRate, double visitorRate) {
		super();
		this.movieId = movieId;
		this.kortitle = kortitle;
		this.engtitle = engtitle;
		this.limitage = limitage;
		this.genre = genre;
		this.relDate = relDate;
		this.director = director;
		this.cast = cast;
		this.runningTime = runningTime;
		this.synopsis = synopsis;
		this.trailer = trailer;
		this.filmType = filmType;
		this.poster = poster;
		this.expertRate = expertRate;
		this.visitorRate = visitorRate;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getKortitle() {
		return kortitle;
	}

	public void setKortitle(String kortitle) {
		this.kortitle = kortitle;
	}

	public String getEngtitle() {
		return engtitle;
	}

	public void setEngtitle(String engtitle) {
		this.engtitle = engtitle;
	}

	public int getLimitage() {
		return limitage;
	}

	public void setLimitage(int limitage) {
		this.limitage = limitage;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRelDate() {
		return relDate;
	}

	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getExpertRate() {
		return expertRate;
	}

	public void setExpertRate(double expertRate) {
		this.expertRate = expertRate;
	}

	public double getVisitorRate() {
		return visitorRate;
	}

	public void setVisitorRate(double visitorRate) {
		this.visitorRate = visitorRate;
	}

	@Override
	public String toString() {
		return "LHJ_MovieVO [movieId=" + movieId + ", kortitle=" + kortitle + ", engtitle=" + engtitle + ", limitage="
				+ limitage + ", genre=" + genre + ", relDate=" + relDate + ", director=" + director + ", cast=" + cast
				+ ", runningTime=" + runningTime + ", synopsis=" + synopsis + ", trailer=" + trailer + ", filmType="
				+ filmType + ", poster=" + poster + ", expertRate=" + expertRate + ", visitorRate=" + visitorRate
				+ ", toString()=" + super.toString() + "]";
	}
		
}
