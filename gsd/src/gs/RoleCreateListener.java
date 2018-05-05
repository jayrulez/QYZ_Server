package gs;

public interface RoleCreateListener {

	void onRoleCreateInProcedure(long roleid);

	void onRoleDeleteInProcedure(long roleid);
}
