package gs;

import common.ErrorCode;
import gm.GmSession;
import lx.gs.SError;
import lx.gs.SError2;
import lx.gs.error.FError;
import lx.gs.login.LoginTraceMgr;
import lx.gs.tips.FTips;
import xdb.Trace;

public abstract class AProcedure<P extends xio.Protocol> extends ProtocolProcedure<P>{
    protected long userid;
    protected long roleid;

    protected AProcedure(P p) {
        super(p);
        gnet.link.Role role = gnet.link.Onlines.getInstance().find(p);
        if(role != null) {
			this.userid = role.getUserid();
			this.roleid = role.getRoleid();
		} else {
		    if(Config.getInstance().isDebug() && GmSession.current() != null) {
                this.roleid = GmSession.current().getRoleid();
                this.userid = xtable.Roleinfos.selectUserid(roleid);
            } else {
                this.roleid = 0;
                this.userid = 0;
            }
		}
    }
    
    @Override
    protected boolean process() throws Exception {
    	try {
			LoginTraceMgr.addProcedureCount(roleid);
    		return doProcess();
    	} catch(SErrorException se) {
			terror(se.errcode);
			return false;
		} catch(SErrorException2 se) {
            if(Config.getInstance().isDebug()) {
                xdb.Transaction.tsend(roleid, new SError2(se.getMessage()));
            }
			return false;
    	} catch(Exception e) {
            Trace.error(String.format("AProcedure. userid:%s roleid:%s msg:%s %s", userid, roleid, param.getClass().getName(), param), e);
            throw e;
    	}
    }

    public static xio.Protocol makeError(ErrorCode err) {
    	return FError.makeError(err);
    }

    private void terror(ErrorCode err) {
    	xdb.Transaction.tsend(roleid, new SError(err.getErrorId()));
    }
    
    protected boolean error(ErrorCode err){
    	terror(err);
        return false;
    }

	protected boolean error(int location, int tips, String... params){
		xdb.Transaction.tsend(roleid, FTips.create(location, tips, params));
		return false;
	}

	protected boolean error(String err) {
        if(Config.getInstance().isDebug()) {
            xdb.Transaction.tsend(roleid, new SError2(err));
        }
		return false;
	}

	protected boolean currencyNotEnough(int currenctyType) {
		terror(ErrorCode.CURRENCY_IS_NOT_ENOUGH);
		return false;
	}
    
    protected final void tsend(xio.Protocol proto) {
    	tsend(roleid, proto);
    }
    
    public final static void tsend(long roleid, xio.Protocol proto) {
    	xdb.Transaction.tsend(roleid, proto);
    }
    
    protected boolean tsendWhileCommit(long roleid, xio.Protocol pro){
    	xdb.Transaction.tsendWhileCommit(roleid, pro);
    	return true;
    }

	protected boolean response(int location, int tips){
		xdb.Transaction.tsendWhileCommit(roleid, FTips.create(location, tips));
		return true;
	}

    protected boolean response(xio.Protocol response) {
        xdb.Transaction.tsendWhileCommit(roleid, response);
        return true;
    }
	
	public final void validateRoleidAndExecute() {
		if(roleid != 0) {
			execute();
		}
	}
	
	private static final class SErrorException extends RuntimeException {
		private static final long serialVersionUID = 3854656145367199856L;
		
		public final ErrorCode errcode;
		public SErrorException(ErrorCode errcode) {
			super("", null, false, false);
			this.errcode = errcode;
		}

	}

	private static final class SErrorException2 extends RuntimeException {
		public SErrorException2(String message) {
			super(message);
		}
	}
	
	public static final RuntimeException createSErrorException(ErrorCode errcode) {
		return new SErrorException(errcode);
	}
	public static final RuntimeException createSErrorException2(String err) {
		return new SErrorException2(err);
	}

	public void setRoleId(long roleid){
		this.roleid = roleid;
	}
}
