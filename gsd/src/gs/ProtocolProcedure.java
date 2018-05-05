package gs;

import xdb.Procedure;
import xio.Protocol;

public abstract class ProtocolProcedure<P extends Protocol> extends Procedure{
    protected P param;

    protected ProtocolProcedure(P p) {
        param = p;
    }

    @Override
    protected boolean process() throws Exception {
        return this.doProcess();
    }

    protected abstract boolean doProcess() throws Exception ;

}
