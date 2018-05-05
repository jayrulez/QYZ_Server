package gs;

public interface RoleLoginListener {

	void afterRoleLoginInProcedure(long roleid);

	void beforeRoleLogoutInProcedure(long roleid);
}
