package absyn;

public class Params extends Absyn {
	public ParamList pList;
	public Boolean isVoid;

	public Params (int pos, ParamList pList) {
		this.pList = pList;
		if (this.pList == null)
			isVoid = true;
		else
			isVoid = false;
	}
}
