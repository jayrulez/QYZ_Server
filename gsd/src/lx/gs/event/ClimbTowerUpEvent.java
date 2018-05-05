package lx.gs.event;

/**
 * @author Jin Shuai
 */
public class ClimbTowerUpEvent extends AbstractEvent{

    public final int floor;
    public final int useTime;
    public ClimbTowerUpEvent(long roleId, int floor, int useTime) {
        super(roleId, EventType.CLIMB_TOWER_UP);
        this.floor = floor;
        this.useTime = useTime;
    }
}
