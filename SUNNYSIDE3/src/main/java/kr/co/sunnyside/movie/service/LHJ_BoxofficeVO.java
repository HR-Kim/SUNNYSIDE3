package kr.co.sunnyside.movie.service;

import kr.co.sunnyside.cmn.DTO;

public class LHJ_BoxofficeVO extends DTO{
	private String movieId    ;
	private String kortitle   ;
	private String engtitle   ;
	private String limitage   ;
	private String genre      ;
	private String relDate    ;
	private String director   ;
	private String cast       ;
	private String runningTime;
	private String synopsis   ;
	private String trailer    ;
	private String filmType   ;
	private String poster     ;
	private String expertRate ;
	private String visitorRate;
	
	public LHJ_BoxofficeVO() {}
	
	public LHJ_BoxofficeVO(String movieId, String kortitle, String engtitle, String limitage, String genre,
			String relDate, String director, String cast, String runningTime, String synopsis, String trailer,
			String filmType, String poster, String expertRate, String visitorRate) {
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

	public String getLimitage() {
		return limitage;
	}

	public void setLimitage(String limitage) {
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

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
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

	public String getExpertRate() {
		return expertRate;
	}

	public void setExpertRate(String expertRate) {
		this.expertRate = expertRate;
	}

	public String getVisitorRate() {
		return visitorRate;
	}

	public void setVisitorRate(String visitorRate) {
		this.visitorRate = visitorRate;
	}

	@Override
	public String toString() {
		return "LHJ_BoxofficeVO [movieId=" + movieId + ", kortitle=" + kortitle + ", engtitle=" + engtitle
				+ ", limitage=" + limitage + ", genre=" + genre + ", relDate=" + relDate + ", director=" + director
				+ ", cast=" + cast + ", runningTime=" + runningTime + ", synopsis=" + synopsis + ", trailer=" + trailer
				+ ", filmType=" + filmType + ", poster=" + poster + ", expertRate=" + expertRate + ", visitorRate="
				+ visitorRate + ", toString()=" + super.toString() + "]";
	}	
	
}

