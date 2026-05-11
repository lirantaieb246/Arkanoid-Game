package game.listeners;

/**
 * The {@code HitNotifier} interface should be implemented by objects that can be "hit"
 * and want to notify registered {@link HitListener}s when a hit event occurs.
 * This allows decoupling the logic that handles hit events from the objects that are hit.
 */
public interface HitNotifier {
    /**
     * Adds a {@link HitListener} to the list of listeners to be notified when a hit occurs.
     *
     * @param hl the {@link HitListener} to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a {@link HitListener} from the list of listeners to stop it from being notified of hits.
     *
     * @param hl the {@link HitListener} to remove
     */
    void removeHitListener(HitListener hl);
}
