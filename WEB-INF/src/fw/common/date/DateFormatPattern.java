package fw.common.date;

public enum DateFormatPattern {

	PATTERN_YYYY("yyyy"),
	PATTERN_M("M"),
	PATTERN_D("d"),
	PATTERN_MM("MM"),
	PATTERN_DD("dd"),
	PATTERN_YYYYMD("yyyyMd"),
	PATTERN_YYYYMMDD("yyyyMMdd"),
	PATTERN_YYYYMMDD_SLASH("yyyy/MM/dd"),
	PATTERN_YYYYMMDD_HYPHEN("yyyy-MM-dd"),
	PATTERN_GGGGYYYYMMDD("GGGGyyyy/MM/dd"),
	PATTERN_GGGGYYYY_MM_DD("GGGGyyyy-MM-dd");

	private String pattern;

	private DateFormatPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
