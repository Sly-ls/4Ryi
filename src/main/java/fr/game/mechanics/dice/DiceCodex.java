package fr.game.mechanics.dice;


import fr.game.constants.game.GameValueEnum;
import fr.game.mechanics.core.Inventory;

import java.util.HashMap;

public enum DiceCodex{

	SCEPTER("SCEPTER",
			"SCEPTER",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2).changeQuantityThen(GameValueEnum.CRITICAL,1));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2).changeQuantityThen(GameValueEnum.CRITICAL,1));}
			}),
	SHORT_BOW("BOW",
			"BOW",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,3).changeQuantityThen(GameValueEnum.CRITICAL,1));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,4));}
				{put(5, new Inventory<GameValueEnum>());}
				{put(6, new Inventory<GameValueEnum>());}
			}),
	SMALL_BLADE("SMALL_BLADE",
			"SMALL_BLADE",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>());}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2).changeQuantityThen(GameValueEnum.CRITICAL,1));}
			}),
	MEDIUM_BLADE("MEDIUM_BLADE",
			"MEDIUM_BLADE", 
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2).changeQuantityThen(GameValueEnum.CRITICAL,1));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,3));}
			}),
	MEDIUM_BLUNT("MEDIUM_BLUNT",
			"MEDIUM_BLUNT",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2).changeQuantityThen(GameValueEnum.CRITICAL,1));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
			}),
	LIGHT_ARMOR("LIGHT_ARMOR",
			"LIGHT_ARMOR",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DODGE,3));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DODGE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DODGE,1));}
			}),
	MEDIUM_ARMOR("MEDIUM_ARMOR",
			"MEDIUM_ARMOR",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DODGE,1));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
			}),
	HEAVY_ARMOR("HEAVY_ARMOR",
			"HEAVY_ARMOR",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,3));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,3));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,3));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,2));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
			}),
	SKILL_DICE("HEAVY_ARMOR",
			"HEAVY_ARMOR",
			"image",
			6,
			new HashMap<Integer, Inventory<GameValueEnum>>() {
				{put(1, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(2, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DAMAGE,1));}
				{put(3, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
				{put(4, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.DEFENSE,1));}
				{put(5, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.CRITICAL,1));}
				{put(6, new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.CRITICAL,1));}
			});

	public final String name;
	public final String description;
	public final String image;
	public final int nbFaces;
	private final HashMap<Integer, Inventory<GameValueEnum>> diceFaceValueMap;

	/*
	 * Constructor
	 */
	private DiceCodex(String name, String description, String image, int nbFaces,
					  HashMap<Integer, Inventory<GameValueEnum>> diceMap) {
		this.diceFaceValueMap = diceMap;
		this.name = name;
		this.description = description;
		this.nbFaces = nbFaces;
		this.image = image;
	}


	/*
	 * M�thode Describe
	 */
//	public static List<Inventory<GameValueDescription>> describeAllFace(){
//
//		List<Inventory<GameValueDescription>> rollResultDescription = new ArrayList<Value>();
//
//		for(DiceCodex diceCodex : values()){
//
//			Value rollResultValues = new Value();
//			GameValue rollTotalResult = new GameValue();
//			rollResultValues.addValue(CstValueType.toRoll, new GameValue(diceCodex,1));
//
//			for (int i = 1; i <= diceCodex.getNbFaces(); i++) {
//
//				DiceValue rolled = diceCodex.getFaceDescription(i);
//				rollResultValues.addValue(CstValueType.rollHistory, rolled);
//				rollTotalResult.addGameValues( rolled.getActualGameValue() );
//
//			}
//
//			rollResultValues.addValue(CstValueType.rollResult, rollTotalResult );
//			rollResultDescription.add(rollResultValues);
//		}
//
//		return rollResultDescription;
//
//	}
//	private DiceValue getFaceDescription(int faceNumber) {
//
//		return new DiceValue(this, faceNumber);
//
//	}

	/*
	 * M�thode Roll
	 */
//	public static Value rollDice(GameValue dicePool){
//
//		Value rollResultValues = new Value();
//		rollResultValues.addValue(CstValueType.toRoll, dicePool);
//		GameValue rollTotalResult = new GameValue();
//
//		for(Constant dc : dicePool.getGameValue().keySet()){
//
//			if(dicePool.getGameValue().get(dc) != null){
//
//				for(int i = 0; i < dicePool.getGameValue().get(dc); i++){
//
//					DiceValue rolled = ((DiceCodex) dc).roll();
//					rollResultValues.addValue(CstValueType.rollHistory, rolled);
//					rollTotalResult.addGameValues( rolled.getActualGameValue() );
//
//				}
//			}
//		}
//
//
//		rollResultValues.addValue(CstValueType.rollResult, rollTotalResult );
//
//		return rollResultValues;
//
//	}
//	private DiceValue roll() {
//
//		return getFaceDescription( DieRoller.jet_de_1_a_X( nbFaces ) );
//
//	}


	/*
	 * Getter
	 */
	public String getName() {
		return name;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public int getNbFaces() {

		return nbFaces;

	}
	public HashMap<Integer, Inventory<GameValueEnum>> getDiceFaceValueMap() {
		return diceFaceValueMap;
	}


}
