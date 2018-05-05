package xauany.code;

import gnet.ConfirmUseActivationCode;

public class PConfirmUseActivationCode extends xdb.Procedure{
	private ConfirmUseActivationCode request;

	public PConfirmUseActivationCode(ConfirmUseActivationCode request) {
		super();
		this.request = request;
	}
	
	@Override
	protected boolean process(){
		long code = CodeUtils.decode(request.code);
		xbean.ActivationCode activationCode = xtable.Activationcodes.get(code);
		if(activationCode.getStatus() == xbean.ActivationCode.STATE_ALLOCATE){
			if(request.isconfirm == 1){
				activationCode.setStatus(xbean.ActivationCode.STATE_CONFIRM);
			}
			else{
				activationCode.setStatus(xbean.ActivationCode.STATE_NEW);
			}
		}
		return true;
	}
}
