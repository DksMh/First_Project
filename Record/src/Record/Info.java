package Record;

public class Info {
	private String name;
	private int ansgame;
	private int totalgame;
	private String date;

	public Info() {
		this("", 0, 0, "");
	}

	public Info(String name, int ansgame, int totalgame, String date) {
		this.name = name;
		this.ansgame = ansgame;
		this.totalgame = totalgame;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAnsgame() {
		return ansgame;
	}

	public void setAnsgame(int ansgame) {
		this.ansgame = ansgame;
	}

	public int getTotalgame() {
		return totalgame;
	}

	public void setTotalgame(int totalgame) {
		this.totalgame = totalgame;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "infom [name=" + name + ", ansgame=" + ansgame + ", totalgame=" + totalgame + ", date=" + date + "]";
	}
	

}
