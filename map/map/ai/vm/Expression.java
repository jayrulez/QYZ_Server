package map.ai.vm;

public abstract class Expression {	
	public boolean entered = false;
	public boolean failed = false;
	protected VM vm;
	
	public final VM getVM() {
		return vm;
	}
	
	public final void init(VM vm) {
		this.vm = vm;
		onInit(vm);
	}
	
	public final boolean execute() {
		if(!entered) {
			entered = true;
			failed = false;
			enter();
		}
		if(run()) {
			return true;
		} else {
			exit();
			entered = false;
			return false;
		}
	}
	
	protected void onInit(VM vm) {}
	public void onDestroy() {}
	public void enter() {}
	public void exit() {}
	public abstract boolean run();

	public boolean onEvt(int eventid, Object param) {
		return false;
	}
}
