/**
 * This Class is a static Class
 * Used to remove safely an Entity
 * 
 * Tip:
 * Use RemoveEntity.removeEntity(yourScene) to remove all children attached to the scene
 * 
 * @author Valentin Rudloff - Gamma Software
 * Open source - Free to use
 */
public class RemoveEntity {

	
	//=================================================
	// STATIC METHOD
	//=================================================
	
	/**
	 * Remove the entity with his children
	 * then dispose
	 * @param entityToRemove
	 */
	public static void removeEntity(Activity activity, final Engine engine, final Entity entityToRemove){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				entityToRemove.clearEntityModifiers();
				entityToRemove.clearUpdateHandlers();
				
				removeChildren(activity, engine, entityToRemove);
				entityToRemove.detachSelf();
				entityToRemove.dispose();
			}
		});
	}
	
	/**
	 * Remove children
	 * then dispose
	 * @param entityToRemove
	 */
	public static void removeChildren(Activity activity, final Engine engine, final Entity entityToRemove){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < entityToRemove.getChildCount(); i++){
					
					final IEntity child = entityToRemove.getChildByIndex(i);
					
					child.clearEntityModifiers();
					child.clearUpdateHandlers();
					
					
					RunnableHandler run = new RunnableHandler(){
						@Override
						public synchronized void postRunnable(Runnable pRunnable) {
							super.postRunnable(pRunnable);
							removeChildren((Entity)child);
							child.detachSelf();
							child.dispose();
						}
						
					};
					engine.registerUpdateHandler(run);
				}
					
			}
		});
	}
	
}
