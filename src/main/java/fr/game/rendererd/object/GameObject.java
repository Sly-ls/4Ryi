package fr.game.rendererd.object;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameCondition;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.game.combat.Skill;
import fr.game.mechanics.game.combat.GameEffect;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.game.GameController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.entity.AbstractEntity;

import java.awt.*;

public class GameObject extends AbstractRendered{

    private int quantity;
    protected GameObjectEnum type;

    protected String name;
    protected ScaledImage image;
    boolean pickup;
    boolean action;
    GameObjectEnum requiredForSucess;

    //CONSTRUCTOR
    public GameObject(GameObjectEnum objectTypeEnum,
                      int worldX, int worldY) {
        super();

        this.type = objectTypeEnum;
        this.name = objectTypeEnum.getName();
        this.solidAreaDefaultX = objectTypeEnum.getSolidAreaDefaultX();
        this.solidAreaDefaultY = objectTypeEnum.getSolidAreaDefaultY();
        this.solidAreaDefaultWitdh = objectTypeEnum.getSolidAreaDefaultWitdh();
        this.solidAreaDefaultHeigth  = objectTypeEnum.getSolidAreaDefaultHeigth();
        this.image = new ScaledImage(objectTypeEnum.getImagesPath());
        this.collision = objectTypeEnum.isCollision();
        this.pickup  = objectTypeEnum.isPickup();
        this.action = objectTypeEnum.isAction();
        this.requiredForSucess = objectTypeEnum.getRequiredForSucess();

        this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, solidAreaDefaultWitdh, solidAreaDefaultHeigth);

        this.worldX = worldX * AppVariables.tileSize;
        this.worldY = worldY * AppVariables.tileSize;
        this.quantity = 1;
    }

    //GRAPHICS METHOD
    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics2D graphics2D){
        draw(graphics2D, 0);
    }
    public void draw(Graphics2D graphics2D, int imageIndex){

        graphics2D.drawImage(image.getBufferedImage(), Camera.getInstance().getScreenX(this.getWorldX()), Camera.getInstance().getScreenY(this.getWorldY()),
                AppVariables.tileSize, AppVariables.tileSize, null);

        if(AppConstants.DEBUG) {
            Color colorBlack = new Color(0, 255, 0, 255);
            graphics2D.setColor(colorBlack);
            graphics2D.fillRect(Camera.getInstance().getScreenX(this.getWorldX()) + this.getSolidArea().x,
                    Camera.getInstance().getScreenY(this.getWorldY()) + this.getSolidArea().y,
                    this.getSolidArea().width,
                    this.getSolidArea().height);
        }
    }

    //ACTION METHODS
    public void use(AbstractEntity entity) {
        if(this.requiredForSucess == null ||entity.getBackpack(this.requiredForSucess) > 0){
            if(this.requiredForSucess != null) {
                GameController.getInstance().getPlayer().getBackpack().changeQuantity(this.requiredForSucess, -1);
            }
            if(pickup){
                entity.pickup(this);
                applyEffects(entity, GameCondition.ON_PICK);
                MessageBox.getInstance().sendMessage(MessageTypeEnum.WORLD_EVENT,"Tu as rammassé " + this.name,
                        FontEnum.FRANCISB,Color.WHITE, 40,
                        false, 28,
                        this.worldX,this.worldY);
                GameController.getInstance().removeObjectFromTheWorld(this);
            }
            if(action){
                applyEffects(entity, GameCondition.ON_SUCCESS);
                MessageBox.getInstance().sendMessage(MessageTypeEnum.MESSAGE_TO_PLAYER,this.getName(),
                        FontEnum.BEDROCK,Color.WHITE, 40,
                        true, 28,
                        Camera.getInstance().getScreenX(this.worldX), Camera.getInstance().getScreenY(this.worldY));
                GameController.getInstance().removeObjectFromTheWorld(this);
            }

        }else{
            applyEffects(entity, GameCondition.ON_FAIL) ;
            MessageBox.getInstance().sendMessage(MessageTypeEnum.WORLD_EVENT,"Il faut une " + this.requiredForSucess.getName()+" !",
                    FontEnum.BEDROCK,Color.WHITE, 40,
                    false, 28,
                    this.worldX,this.worldY);

        }
    }
    public void applyEffects(AbstractEntity entity, GameCondition condition){
        //FIXME use the condition to determine the type of effect, should be a map in game object and game event;
        // more will follow, having effect wil be pretty common within enum,
        // for traits, race, class modifier, weapons/armor/object/special effect
        // action effect...
        // May be there should be a class for handling an effect description without instanciating the effect...
        // Because if we instantiante the effect, all people action will use the same version of the attack..
        // wait.. may be i wish i could do so...

        if(this.type != null && this.type.getEffectType() != null) {
            GameEffect eventEffect = new GameEffect(this.type.getEffectType(), this.type.getPower(), this.type.getTargetCoordinate(), GameCondition.ON_HIT);
            eventEffect.setTargetAttribute(this.type.getTargetAttribute());
            entity.receiveEffects(new Skill(this, eventEffect));
        }
    }
    // GETTER AND SETTER
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ScaledImage getImage() {
        return image;
    }
    public void setImage(ScaledImage image) {
        this.image = image;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public GameObjectEnum getType() {
        return type;
    }
    public void setType(GameObjectEnum type) {
        this.type = type;
    }
    public int getQuantity() {
        return quantity;
    }
}
