/**
 * This Class is a static Class
 * Used to remove safely an Entity
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
	public static void removeEntity(final Entity entityToRemove){
		ResourceManager.getInstance().activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				entityToRemove.clearEntityModifiers();
				entityToRemove.clearUpdateHandlers();
				
				removeChildren(entityToRemove);
				entityToRemove.detachSelf();
				entityToRemove.dispose();
			}
		});
	}
	
	/**
	 * Remove children
	 * @param entityToRemove
	 */
	public static void removeChildren(final Entity entityToRemove){
		ResourceManager.getInstance().activity.runOnUiThread(new Runnable() {
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
					ResourceManager.getInstance().engine.registerUpdateHandler(run);
				}
					
			}
		});
	}
	
}
