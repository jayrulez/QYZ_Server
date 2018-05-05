package map.controller;

public abstract class CfgDeployment<T extends cfg.map.Deployment> extends Deployment {
	protected final T deploymentCfg;
    protected boolean closed;
	public CfgDeployment(Controller controller, T deploymentCfg) {
		super(controller);
		this.deploymentCfg = deploymentCfg;
        this.closed = false;
	}
}
