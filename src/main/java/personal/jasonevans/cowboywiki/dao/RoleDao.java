package personal.jasonevans.cowboywiki.dao;

import personal.jasonevans.cowboywiki.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String roleName);
}
