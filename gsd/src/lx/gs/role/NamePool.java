package lx.gs.role;

import cfg.CfgMgr;
import cfg.role.NamePosition;
import lx.gs.login.FLogin;

import java.util.List;

public class NamePool {
    public final static NamePool Ins = new NamePool();

	private final int totalWeight;
    private NamePool(){
		totalWeight = CfgMgr.name.deconames.stream().mapToInt(d -> d.weight).sum();
    }

	private String randomFirstName(int gender) {
		final List<String> names = CfgMgr.name.firstnames.get(gender).names;
		return names.get(common.Utils.random().nextInt(names.size()));
	}

	private String randomLastName() {
		final List<String> names = CfgMgr.name.lastnames.names;
		return names.get(common.Utils.random().nextInt(names.size()));
	}

	private cfg.role.DecorateName randomDecorateName() {
		int w = common.Utils.random().nextInt(totalWeight);
		for(cfg.role.DecorateName n : CfgMgr.name.deconames) {
			if((w -= n.weight) < 0)
				return n;
		}
		return null;
	}
    
    private String random(int gender){
		final cfg.role.DecorateName decorateName = randomDecorateName();
		final String lastName = randomFirstName(gender);//配置表里面配置的LastName实际上是姓
		final String firstName = randomLastName();
		switch (decorateName.position) {
			case NamePosition.FROND: {
				return decorateName.name + firstName + lastName;
			}
			case NamePosition.MIDDLE: {
				return firstName + decorateName.name + lastName;
			}
			case NamePosition.END: {
				return firstName + lastName + decorateName.name;
			}
			default:
				throw new RuntimeException("unknown nameposition:" + decorateName.position);
		}
    }
    
    public String randomUniqueName(int gender){
		String name = "";
    	for(int i = 0 ; i < 20 ; i++) {
			name = random(gender);
            if(FRole.isSenseWord(name))
                continue;
			final String fullName = FLogin.makeFullName(name, gs.Utils.getServerId());
			if(xtable.Rolename2ids.getTable().select(fullName, ri -> ri) == null)
				return name;
		}
    	
    	return name;
    }

	public void test() {
		for(int i = 0 ; i < 100 ; i++) {
			System.out.println(randomUniqueName(0) + "  " + randomUniqueName(1));
		}
	}
}
