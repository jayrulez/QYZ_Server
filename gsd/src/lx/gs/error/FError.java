package lx.gs.error;

import common.ErrorCode;
import gnet.link.Onlines;
import gs.AProcedure;
import lx.gs.SError;

public final class FError {
	public static xio.Protocol makeError(ErrorCode err) {
		return new SError(err.getErrorId());
	}

	public static void sendNotProcedureError(long roleid, ErrorCode err) {
		Onlines.getInstance().send(roleid, makeError(err));
	}

	public static boolean sendProcedureError(long roleid, ErrorCode err) {
		xdb.Transaction.tsend(roleid, makeError(err));
		return false;
	}
	
	public final static void check(ErrorCode err) {
		if(err.err())
			error(err);
	}
	
    public final static void error(ErrorCode err) {
    	throw AProcedure.createSErrorException(err);
    }

	public final static void error(String err) {
		throw AProcedure.createSErrorException2(err);
	}
    
}
