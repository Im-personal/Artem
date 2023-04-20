package artem.semmich.cookie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;


public class Graph extends ApplicationAdapter {
	static SpriteBatch batch;
	Texture img;

	static int width = 0;
	static int height = 0;//1440 720

	static float mnX = 1;
	static float mnY = 1;




	Preferences prefs;
	@Override
	public void create () {
		//delay(5000);

		prefs = Gdx.app.getPreferences("data");

		event = prefs.getInteger("event",0);
		System.out.println("EVENT IS:::::::: "+event);
		batch = new SpriteBatch();

		width=Gdx.graphics.getWidth();
		height=Gdx.graphics.getHeight();
		mnX = width/1440f;
		mnY = height/720f;
		Gdx.input.setInputProcessor(new Mistener());

		map=new Animation[]{
				new Animation("sprites/black",1),
				new Animation("sprites/white",1),
		};

		snowdinmap = new Animation[]
				{
						new Animation("sprites/snowdin/snow4",1),
						new Animation("sprites/snowdin/snow3",1),
						new Animation("sprites/snowdin/snow2",1),
						new Animation("sprites/snowdin/snow1",1),
				};

		sansface = new Animation[]
				{
						new Animation("sprites/sans/face1",4),
						new Animation("sprites/sans/face2",4),
						new Animation("sprites/sans/face3",4),
						new Animation("sprites/sans/face4",4),
				};

		papyface = new Animation[]
				{
						new Animation("sprites/papyrus/face1",10),
						new Animation("sprites/papyrus/face2",10),
						new Animation("sprites/papyrus/face3",10),
				};


		Animation[] tree = new Animation[]{new Animation("sprites/snowdin/tree",1)};
		soul = new Animation("sprites/soul",8);
		testroom=new Room("rooms/room0",map);
		snowdin = new Room("rooms/snowdin",snowdinmap);
		papyroom = new Room("rooms/house",map);
		nowroom=testroom;
		artem = new Thing(0,0,90,180*(48/36f),new Animation[]{
				new Animation("sprites/inwater/down",4),
				new Animation("sprites/inwater/up",4),
				new Animation("sprites/inwater/left",4),
				new Animation("sprites/inwater/right",4),
		},0);


		anotartem = new Animation[]
				{
						new Animation("sprites/deltaruneArtem/down",4),
						new Animation("sprites/deltaruneArtem/up",4),
						new Animation("sprites/deltaruneArtem/left",4),
						new Animation("sprites/deltaruneArtem/right",4),
				};

		omoriartem = new Animation[]
				{
						new Animation("sprites/omori/artem/walk/down",4),
						new Animation("sprites/omori/artem/walk/up",4),
						new Animation("sprites/omori/artem/walk/left",4),
						new Animation("sprites/omori/artem/walk/right",4),
						new Animation("sprites/omori/artem/run/down",4),
						new Animation("sprites/omori/artem/run/up",4),
						new Animation("sprites/omori/artem/run/left",4),
						new Animation("sprites/omori/artem/run/right",4),
				};

		omorisans = new Animation[]
				{
					new Animation("sprites\\omori\\sans\\walking\\back",4),
					new Animation("sprites\\omori\\sans\\walking\\front",4),
				};

		sammichface = new Texture[]
				{
						new Texture("sprites\\omori\\sam\\dialogue\\1.png"),
						new Texture("sprites\\omori\\sam\\dialogue\\2.png"),
				};
		cux = new Texture("sprites\\omori\\cookie\\dialogue.png");

		omoripap = new Animation[]
				{
						new Animation("sprites\\omori\\papy\\back",4),
						new Animation("sprites\\omori\\papy\\front",4),
				};

		sammich = new Thing(560,360,40,55, new Animation[]
				{
					new Animation("sprites\\omori\\sam\\walking\\back",4),
					new Animation("sprites\\omori\\sam\\walking\\front",4),
					new Animation("sprites\\omori\\sam\\walking\\lside",4),
					new Animation("sprites\\omori\\sam\\walking\\rside",4),
				},0,true,true);

		cookie = new Thing(493,412,40*(27/18f),40*(22/18f), new Animation[]
				{
						new Animation("sprites\\omori\\cookie\\cux",4),
				},4,true,true);

		baloon = new Thing[]
				{
						new Thing(397,207,40*(16/18f),40*(31/18f),new Animation[]{new Animation("sprites\\omori\\obj\\balloon1",1)},0,true,true),
						new Thing(759,219,40*(16/18f),40*(31/18f),new Animation[]{new Animation("sprites\\omori\\obj\\balloon2",1)},0,true,true),
						new Thing(405,358,40*(16/18f),40*(31/18f),new Animation[]{new Animation("sprites\\omori\\obj\\balloon3",1)},0,true,true),
						new Thing(746,387,40*(16/18f),40*(31/18f),new Animation[]{new Animation("sprites\\omori\\obj\\balloon4",1)},0,true,true),
				};
		basket = new Thing(648,305,40*(25/18f),40*(28/18f),new Animation[]{new Animation("sprites\\omori\\obj\\basket",1)},0,true,true);
		//blanket = new Thing(477,268,40*(128/18f),40*(94/18f),new Animation[]{new Animation("sprites\\omori\\obj\\blanket",1)},0,true,true);
		cake = new Thing(485,329,40*(28/18f),40*(30/18f),new Animation[]{new Animation("sprites\\omori\\obj\\cake",1)},0,true,true);
		caps = new Thing(545,306,40*(69/18f),40*(23/18f),new Animation[]{new Animation("sprites\\omori\\obj\\caps",1)},0,true,true);
		drinks = new Thing(459,288,40*(27/18f),32*(23/18f),new Animation[]{new Animation("sprites\\omori\\obj\\drinks",1)},0,true,true);
		pizza = new Thing(685,367,40*(27/18f),32*(22/18f),new Animation[]{new Animation("sprites\\omori\\obj\\pizza",1)},0,true,true);
		present = new Thing[]{
				new Thing(369,252,40*(17/18f),32*(15/18f),new Animation[]{new Animation("sprites\\omori\\obj\\present1",1)},0,true,true),
				new Thing(839,506,40*(19/18f),32*(22/18f),new Animation[]{new Animation("sprites\\omori\\obj\\present2",1)},0,true,true),

		};
				basil = new Thing(705,153,40,55,new Animation[]
				{
						new Animation("sprites/omori/basil/walk/down",4),
						new Animation("sprites/omori/basil/walk/up",4),
						new Animation("sprites/omori/basil/walk/left",4),
						new Animation("sprites/omori/basil/walk/right",4),
						new Animation("sprites/omori/basil/run/down",4),
						new Animation("sprites/omori/basil/run/up",4),
						new Animation("sprites/omori/basil/run/left",4),
						new Animation("sprites/omori/basil/run/right",4),
				},0);
		basil.nowanim=1;

		omoriartemcrown = new Animation[]
				{
						new Animation("sprites/omori/artem/walk/downcrown",4),
						new Animation("sprites/omori/artem/walk/upcrown",4),
						new Animation("sprites/omori/artem/walk/leftcrown",4),
						new Animation("sprites/omori/artem/walk/rightcrown",4),
						new Animation("sprites/omori/artem/run/downcrown",4),
						new Animation("sprites/omori/artem/run/upcrown",4),
						new Animation("sprites/omori/artem/run/leftcrown",4),
						new Animation("sprites/omori/artem/run/rightcrown",4),
				};


		amogus = new Thing[]
				{
						new Thing(620,21,202,166,new Animation[]{new Animation("sprites\\omori\\amogus\\1",131)},131,true,true),
						new Thing(314,229,194,218,new Animation[]{new Animation("sprites\\omori\\amogus\\2",131)},131,true,true),
						new Thing(1030,262,200,251,new Animation[]{new Animation("sprites\\omori\\amogus\\3",131)},131,true,true),
						new Thing(831,281,57,65,new Animation[]{new Animation("sprites\\omori\\amogus\\4",132)},132,true,true),
				};

		sans = new Thing(0,0,90*(23/19f),180*(30/36f), new Animation[]{
				new Animation("sprites/sans/down",4),
				new Animation("sprites/sans/up",4),
				new Animation("sprites/sans/left",4),
				new Animation("sprites/sans/right",4),
		},18,true);

		papyrus = new Thing(-9999,999999,90*(25/19f),180*(42/36f), new Animation[]{
				new Animation("sprites/papyrus/down",4),
				new Animation("sprites/papyrus/up",4),
				new Animation("sprites/papyrus/left",4),
				new Animation("sprites/papyrus/right",4),
		},0);
		guy = new Thing(500,500,90,180,new Animation[]{new Animation("sprites/talkguy",2)},1);
		graydoor = new Thing(720*2,170-205,135,205,new Animation[]{
				new Animation("sprites/graydoor",1)
		},1);
		tv = new Thing(563.0112f ,536.7135f,90*(52/19f),180*(46/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/tvoff",5),
				new Animation("sprites/papyhomestuff/tv",5),
		},101,true);

		painting = new Thing(633.87683f ,106.7144f,90*(47/19f),180*(30/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/pictureoff",5),
				new Animation("sprites/papyhomestuff/picture",10),
		},85);

		booktable = new Thing(117.16623f ,964.0823f,90*(25/19f),180*(25/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/booktable",5),
		},87,true);

		stonetable = new Thing(1245.0f,737.69f,90*(37/19f),180*(68/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/stonetable",5),
		},102,true,true);
		sofa = new Thing(477.69186f,933.97644f,90*(80/19f),180*(36/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/sofa",5),
		},100,true);

		sansdoor = new Thing(1151.6897f ,55.486435f,90*(36/19f),180*(49/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/door",1),
		},93);
		papydoor = new Thing(179.59317f ,55.486435f,90*(36/19f),180*(49/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/door",1),
		},38);
		//tv = new Thing(0,0,90*(/19f),180*(42/36f))

		box1 = new Thing(1028.5474f ,657.2727f,90*(18/19f),180*(17/36f),new Animation[]{
				new Animation("sprites/papyhomestuff/box/box1",1),
		},106,true);
		box2 = new Thing(
				1146.8774f ,641.8837f,90*(21/19f),180*(23/36f),new Animation[]{
						new Animation("sprites/papyhomestuff/box/box2",1),
				},107,true);
		box3 = new Thing(412.67114f ,634.3813f,90*(26/19f),180*(25/36f),new Animation[]{
						new Animation("sprites/papyhomestuff/box/box3",1),
				},108,true);

		artefact= new Thing(2961.9128f ,748.4933f,90*(24/19f),180*(22/36f),new Animation[]{
				new Animation("sprites/snowdin/artefact",1),
		},113,true);
		ball = new Thing(2611.9128f ,553.4933f+90,90*(16/19f),180*(16/36f),new Animation[]{
				new Animation("sprites/snowdin/ball",1),
		},116,true);
		eggs = new Thing(3015.9128f ,615.4933f,90*(28/19f),180*(18/36f),new Animation[]{
				new Animation("sprites/snowdin/egg",1),
		},112,true);
		gift= new Thing(2326.9128f ,998.4933f,90*(19/19f),180*(18/36f),new Animation[]{
				new Animation("sprites/snowdin/gift",1),
		},117,true);
		trash= new Thing(2367.9248f ,908.7916f-180*2,90*(17/19f),180*(20/36f),new Animation[]{
				new Animation("sprites/snowdin/thrash",1),
		},120,true);
		trash2= new Thing(912.7687f ,462.70605f,90*(56/19f),180*(63/36f),new Animation[]{
				new Animation("sprites/snowdin/thrash2",1),
		},119,true);

		img_found = new Texture("sprites/react.png");


		for(int i = 0; i<10;i++)
		{
			snowdin.addThing(new Thing(1000+180*(55/36f)*i,451-180*(30/36f)*3,180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*i,451-180*(30/36f)*2,180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*i,451-180*(30/36f),180*(60/36f),180*(60/36f),tree,0,true));

			snowdin.addThing(new Thing(1000+180*(55/36f)*i,1176-180,180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*i,1176-180+180*(30/36f),180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*i,1176-180+180*(30/36f)*2,180*(60/36f),180*(60/36f),tree,0,true));

			snowdin.addThing(new Thing(1000-180*(55/36f),451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000-180*(55/36f)*2,451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000-180*(55/36f)*3,451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));

			snowdin.addThing(new Thing(1000+180*(55/36f)*8,451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*9,451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));
			snowdin.addThing(new Thing(1000+180*(55/36f)*10,451+180*(30/36f)*(i-3),180*(60/36f),180*(60/36f),tree,0,true));




		}
		snowdin.addThing(sans);
		testroom.addThing(artem);
		snowdin.addThing(artem);
		//testroom.addThing(guy);
		testroom.addThing(graydoor);

		papyroom.addThing(sans);
		papyroom.addThing(artem);
		papyroom.addThing(tv);
		papyroom.addThing(painting);
		papyroom.addThing(booktable);
		papyroom.addThing(stonetable);
		papyroom.addThing(sofa);
		papyroom.addThing(sansdoor);
		papyroom.addThing(papydoor);
		papyroom.addThing(papyrus);
		papyroom.addThing(box1);
		papyroom.addThing(box2);
		papyroom.addThing(box3);
		snowdin.addThing(artefact);
		snowdin.addThing(ball);
		snowdin.addThing(eggs);
		snowdin.addThing(gift);
		snowdin.addThing(trash);
		snowdin.addThing(trash2);
		btn_Z = new Button(new Texture("sprites/zbutton.png"),1089,407,178,253);
		btn_Z.setPushedTexture(new Texture("sprites/zbuttonpressed.png"));

		joy = new Button(new Texture("sprites/joy.png"),53,336,354,354);
		stick = new Texture("sprites/stick.png");
		img_papyroom = new Texture("sprites/papyhouse.png");
		voice_papyrus = Gdx.audio.newSound(Gdx.files.internal("sound/papy.mp3"));
		photo = Gdx.audio.newSound(Gdx.files.internal("sound/photo.mp3"));
		img_photo = new Texture("sprites\\omori\\photo.png");
		battlestart = Gdx.audio.newSound(Gdx.files.internal("sound/battlestart.mp3"));
		voice_papyrus2 = Gdx.audio.newSound(Gdx.files.internal("sound/papy.mp3"));
		voice_sans = Gdx.audio.newSound(Gdx.files.internal("sound/sans.mp3"));
		voice_omori = Gdx.audio.newSound(Gdx.files.internal("sound/omori.ogg"));
		gasblas = Gdx.audio.newSound(Gdx.files.internal("sound/gasblas.mp3"));
		dooropen = Gdx.audio.newSound(Gdx.files.internal("sound/mus-dooropen.mp3"));
		doorclose = Gdx.audio.newSound(Gdx.files.internal("sound/mus-doorclose.mp3"));
		splash = Gdx.audio.newSound(Gdx.files.internal("sound/snd-splash.mp3"));
		voice_mind = Gdx.audio.newSound(Gdx.files.internal("sound/mind.mp3"));
		sound_enemy = Gdx.audio.newSound(Gdx.files.internal("sound/enemy.mp3"));
		sound_damage = Gdx.audio.newSound(Gdx.files.internal("sound/damage.mp3"));
		mus_sans = Gdx.audio.newMusic(Gdx.files.internal("sound/sansmelody.mp3"));
		mus_trees = Gdx.audio.newMusic(Gdx.files.internal("sound/trees.mp3"));
		mus_bd = Gdx.audio.newMusic(Gdx.files.internal("sound/bd.mp3"));
		mus_sans.setLooping(true);
		papytheme = Gdx.audio.newMusic(Gdx.files.internal("sound/papytheme.mp3"));
		papytheme.setLooping(true);
		mus_trees.setLooping(true);
		mus_bd.setLooping(true);

		mus_sansnomel = Gdx.audio.newMusic(Gdx.files.internal("sound/sansnomelody.mp3"));
		mus_sansnomel.setLooping(true);

		font_determination = generateFont("fonts/determination.otf",1,1,Color.WHITE);
		font_determinationyellow = generateFont("fonts/determination.otf",1,1,Color.YELLOW);
		font_sans = generateFont("fonts/sans.ttf",1,1,Color.WHITE);
		font_papyrus = generateFont("fonts/papyrus.otf",1,1,Color.WHITE);
		font_papyrustiny = generateFont("fonts/papyrus.otf",1/2f,1/2f,Color.BLACK);
		font_omori = generateFont("fonts/omori.ttf",1,1,Color.WHITE);

		btn_battle = new Button[]
				{
						new Button(new Texture("sprites/fight.png"),0,0,0,0),
						new Button(new Texture("sprites/act.png"),0,0,0,0),
						new Button(new Texture("sprites/item.png"),0,0,0,0),
						new Button(new Texture("sprites/spare.png"),0,0,0,0),

				};
		btn_battle[0].setPushedTexture(new Texture("sprites/fightp.png"));
		btn_battle[1].setPushedTexture(new Texture("sprites/actp.png"));
		btn_battle[2].setPushedTexture(new Texture("sprites/itemp.png"));
		btn_battle[3].setPushedTexture(new Texture("sprites/sparep.png"));

		img_dialbubble = new Texture("sprites/text.png");


		atk_bone = new Attack(new Texture("sprites/attacks/bone.png"),796,413,40,129);
		blaster = new Texture("sprites/attacks/blaster.png");

		graydoorpap = new Animation[]{new Animation("sprites/papyhomestuff/graydoor",200)};


		papyarmback = new Texture("sprites\\papyrus\\battlebody\\backhand.png");
		papyarmfront = new Texture("sprites\\papyrus\\battlebody\\fronthand.png");
		papylegs = new Texture("sprites\\papyrus\\battlebody\\legs.png");
		bodysad = new Texture("sprites\\papyrus\\battlebody\\bodysad.png");
		bodypres = new Texture("sprites\\papyrus\\battlebody\\bodypres.png");
		bodymad = new Texture("sprites\\papyrus\\battlebody\\bodymad.png");
		papyscarf = new Animation("sprites\\papyrus\\battlebody\\scarf",5);




		omoristart = new Room("rooms/omoristart",map);
		omoristart.addThing(new Thing(683,343,69,98));
		omoristart.addThing(new Thing(73,0,360,346));
		omoristart.addThing(new Thing(424,0,95,289));
		omoristart.addThing(new Thing(499,0,128,239));
		omoristart.addThing(new Thing(587,0,265,88));
		omoristart.addThing(new Thing(821,10,565,231));
		omoristart.addThing(new Thing(940,151,419,137));
		omoristart.addThing(new Thing(1075,194,317,140));
		omoristart.addThing(new Thing(1206,96,186,619));
		omoristart.addThing(new Thing(874,427,77,111));
		omoristart.addThing(new Thing(812,476,70,96));
		omoristart.addThing(new Thing(950,484,422,92));
		omoristart.addThing(new Thing(449,569,236,150));
		omoristart.addThing(new Thing(348,426,280,171));
		omoristart.addThing(new Thing(36,429,462,131));
		omoristart.addThing(new Thing(95,189,129,385));
		omoristart.addThing(new Thing(816,544,115,161));
		omoristart.addThing(new Thing(757,626,165,91));
		omoristart.addThing(artem);
		omoristart.addThing(amogus[0]);
		omoristart.addThing(amogus[1]);
		omoristart.addThing(amogus[2]);
		omoristart.addThing(amogus[3]);
		omoriway = new Room("rooms/omoristart",map);
		omoriway.addThing(artem);
		omoriway.addThing(new Thing(488,0,82,720));
		omoriway.addThing(new Thing(813,0,143,720));
		omoriway.addThing(new Thing(452,0,169,720));
		omoriway.addThing(new Thing(560,0,91,41));
		omoriway.addThing(new Thing(559,126,127,91));
		omoriway.addThing(new Thing(495,316,189,110));
		omoriway.addThing(new Thing(474,478,212,237));
		omoriway.addThing(new Thing(749,0,158,77));
		omoriway.addThing(new Thing(750,175,136,95));
		omoriway.addThing(new Thing(754,377,248,273));
		omoriway.addThing(new Thing(820,568,135,135));
		omoritobasil = new Room("rooms/omoristart",map);
		omoritobasil.addThing(artem);
		omoritobasil.addThing(new Thing(1183,0,125,720));
		omoritobasil.addThing(new Thing(75,0,232,120));
		omoritobasil.addThing(new Thing(813,78,125,96));
		omoritobasil.addThing(new Thing(943,125,136,248));
		omoritobasil.addThing(new Thing(0,499,370,218));
		omoritobasil.addThing(new Thing(368,0,381,134));
		omoritobasil.addThing(new Thing(558,422,111,216));
		omoritobasil.addThing(new Thing(620,111,129,114));
		omoritobasil.addThing(new Thing(365,310,75,112));
		//omoritobasil.addThing(new Thing(557,345,386,151));
		omoritobasil.addThing(new Thing(572,367,369,137));
		omoritobasil.addThing(new Thing(793,380,148,249));
		omoritobasil.addThing(new Thing(0,720,1440,249));
		omoritobasil.addThing(new Thing(79,0,157,720));
		omoritobasil.addThing(new Thing(614,566,81,95));
		omoritobasil.addThing(new Thing(815,580,62,97));
		omoritobasil.addThing(new Thing(437,208,118,107));
		omoritobasil.addThing(new Thing(1071,254,191,463));
		omoritobasil.addThing(new Thing(235,3,350,247));
		omoritobasil.addThing(new Thing(61,214,313,474));
		omoritobasil2 = new Room("rooms/omoristart",map);
		omoritobasil2.addThing(artem);
		omoritobasil2.addThing(new Thing(25,3,251,712));
		omoritobasil2.addThing(new Thing(207,412,359,307));
		omoritobasil2.addThing(new Thing(456,350,220,122));
		omoritobasil2.addThing(new Thing(765,358,175,114));
		omoritobasil2.addThing(new Thing(815,629,544,86));
		omoritobasil2.addThing(new Thing(1086,0,289,720));
		omoritobasil2.addThing(new Thing(279,313,325,162));
		omoritobasil2.addThing(new Thing(202,273,283,128));
		omoritobasil2.addThing(new Thing(172,35,245,353));
		omoritobasil2.addThing(new Thing(249,42,245,98));
		omoritobasil2.addThing(new Thing(341,23,210,69));
		omoritobasil2.addThing(new Thing(378,0,695,32));
		omoritobasil2.addThing(new Thing(836,0,295,89));
		omoritobasil2.addThing(new Thing(896,48,229,82));
		omoritobasil2.addThing(new Thing(949,105,111,205));
		omoritobasil2.addThing(new Thing(886,281,135,129));
		omoritobasil2.addThing(new Thing(768,310,151,158));
		omoritobasil2.addThing(new Thing(891,289,109,148));
		omoritobasil2.addThing(new Thing(561,66,253,156));
		omoritobasil2.addThing(new Thing(573,194,35,68));
		omoribasil = new Room("rooms/omoristart",map);
		omoribasil.addThing(artem);
		omoribasil.addThing(basil);

		omoribdplace = new Room("rooms/omoristart",map);
		omoribdplace.addThing(artem);
		omoribdplace.addThing(basil);
		omoribdplace.addThing(sans);
		omoribdplace.addThing(papyrus);
		omoribdplace.addThing(cookie);
		omoribdplace.addThing(sammich);
		omoribdplace.addThing(amogus[3]);
		omoribdplace.addThing(baloon[0]);
		omoribdplace.addThing(baloon[1]);
		omoribdplace.addThing(baloon[2]);
		omoribdplace.addThing(baloon[3]);
		omoribdplace.addThing(basket);
		//omoribdplace.addThing(blanket);
		omoribdplace.addThing(cake);
		omoribdplace.addThing(caps);
		omoribdplace.addThing(drinks);
		omoribdplace.addThing(pizza);
		omoribdplace.addThing(present[0]);
		omoribdplace.addThing(present[1]);
		omoribdplace.addThing(new Thing(1254,267,71,107));
		omoribdplace.addThing(new Thing(37,0,65,720));
		omoribdplace.addThing(new Thing(1346,0,48,720));
		omoribdplace.addThing(new Thing(0,691,1429,26));
		omoribdplace.addThing(new Thing(810,266,192,155));
		omoribdplace.addThing(new Thing(82,0,1307,32));
		omoribdplace.addThing(new Thing(157,596,104,109));
		//TODO

		omorimap =new Texture[]
				{
						new Texture("sprites/omori/room1.png"),
						new Texture("sprites/omori/pbroom.png"),
						new Texture("sprites/omori/room3.png"),
						new Texture("sprites/omori/room4.png"),
						new Texture("sprites/omori/room5.png"),
						new Texture("sprites/omori/room6.png"),
						new Texture("sprites/omori/room7.png"),
						new Texture("sprites/omori/room8.png"),
						new Texture("sprites/omori/room9.png"),
						new Texture("sprites/omori/room10.png"),
				};

		basilface = new Texture[]
				{
						new Texture("sprites\\omori\\basil\\basilnormal.png"),
						new Texture("sprites\\omori\\basil\\basilnormal2.png"),
						new Texture("sprites\\omori\\basil\\basilsmile.png"),
						new Texture("sprites\\omori\\basil\\basilbad1.png"),
						new Texture("sprites\\omori\\basil\\basilbad2.png"),
						new Texture("sprites\\omori\\basil\\basilbad3.png"),
						new Texture("sprites\\omori\\basil\\basilok.png"),
				};

		artemwater=new Texture("sprites\\omori\\1.png");
		basilwater=new Texture("sprites\\omori\\2.png");
		crowning=new Animation("sprites\\omori\\crownon",5,false);


		if(event!=0)
		{
			artem.x = prefs.getFloat("artx");
			artem.y = prefs.getFloat("arty");
			artem.anim = anotartem;
			artem.h = 180;
			artem.nowanim=1;
			papydoor.setFrame(1);
			papyrus.x=208.439f;
			papyrus.y=90.90141f;
			sans.x = 999999;
			papydoor.setFrame(1);
			nowroom = papyroom;
			tv.nowanim=1;
			sofa.setFrame(1);
			ztm=1;
			nextEvent(71);
			sofa.event = 104;
		}


	}


	Texture stick;
	Texture img_papyroom;
	Texture img_dialbubble;
	Texture img_found;

	Texture papylegs;
	Texture papyarmback;
	Texture papyarmfront;
	Texture bodysad;
	Texture bodypres;
	Texture bodymad;
	Texture artemwater;
	Texture basilwater;

	Texture[] omorimap;
	Texture[] basilface;


	Animation papyscarf;

	Button joy;
	static Button btn_Z;
	static Button[] btn_battle;


	static Thing artem;
	static Thing basil;
	Thing graydoor;
	Thing guy;
	Thing sans;
	Thing papyrus;

	Thing cookie;
	Thing sammich;
	Thing basket;
	Thing blanket;
	Thing cake;
	Thing caps;
	Thing drinks;
	Thing pizza;
	Thing[] present;
	Thing[] baloon;

	Thing tv;
	Thing booktable;
	Thing stonetable;
	Thing box1;
	Thing box2;
	Thing box3;
	Thing painting;
	Thing sofa;
	Thing sansdoor;
	Thing papydoor;

	Thing artefact;
	Thing ball;
	Thing eggs;
	Thing gift;
	Thing trash;
	Thing trash2;
	Thing[] amogus;


	Animation[] omoripap;
	Animation[] omorisans;
	Animation[] map;
	Animation[] snowdinmap;

	Animation[] anotartem;
	Animation[] omoriartem;
	Animation[] omoriartemcrown;


	Animation[] sansface;
	Animation[] papyface;
	Animation soul;
	Animation[] graydoorpap;

	Room testroom;
	Room snowdin;
	Room papyroom;
	Room omoristart;
	Room omoribdplace;
	Room omoriway;
	Room omoritobasil;
	Room omoritobasil2;
	Room omoribasil;

	Sound splash;
	Sound dooropen;
	Sound doorclose;

	Sound voice_sans;
	Sound voice_omori;
	Sound gasblas;
	static Sound voice_papyrus;
	static Sound voice_papyrus2;
	Sound sound_enemy;
	Sound sound_damage;
	static Sound voice_mind;
	Sound battlestart;

	Music mus_sans;
	Music mus_sansnomel;
	Music papytheme;
	Music mus_trees;
	Music mus_bd;

	BitmapFont font_sans;
	BitmapFont font_papyrus;
	BitmapFont font_omori;
	static BitmapFont font_papyrustiny;
	static BitmapFont font_determination;
	BitmapFont font_determinationyellow;

	static int event = 0;

	static Room nowroom;
	static Dialog nowdialog;
	static Question nowquestion;



	static class Attack{
		public float x,y,w,h;
		public Texture txtr;
		public Attack(Texture txtr,float x, float y, float w, float h)
		{
			this.x = x;
			this.y =y;
			this.w = w;
			this.h =h;
			this.txtr = txtr;
		}
	}

	Attack atk_bone;

	@Override
	public void render () {


		batch.begin();
		if(nowroom==snowdin)
		drawSprite(snowdinmap[0],0,0,1440,720);
		else
			drawSprite(map[0],0,0,1440,720);
		draw(batch);
		count();



		if(event>190)
		{
			drawSprite(map[1],0,0,1440,720);
			batch.setColor(1,1,1,ztm);

				drawSprite(img_photo,386,143,632,484);
			batch.setColor(1,1,1,1);
		}


		batch.end();





	}

	static class Soul{
		static float x=0;
		static float y = 0;
		static int hp = 20;
		static int maxhp = 20;
	}

	static class Field{
		static float x;
		static float y;
		static float w;
		static float h;
		static void lol(float x, float y, float w, float h)
		{
			Field.x = x;
			Field.y = y;
			Field.w = w;
				Field.h = h;
		}
	}

	void draw(SpriteBatch b)
	{
		if(nowroom==papyroom)
		drawSprite(img_papyroom,-eyeX,-eyeY,90*(324f/19),180*(244f/36));

		if(artem.w==40)
		{
			if(nowroom==omoristart)
				drawSprite(omorimap[0],90,0,1260,720);
			else if(nowroom==omoriway)
				drawSprite(omorimap[6],90,0,1260,720);
			else if(nowroom==omoritobasil)
				drawSprite(omorimap[7],90,0,1260,720);
			else if(nowroom==omoritobasil2)
				drawSprite(omorimap[8],90,0,1260,720);
			else if(nowroom==omoribasil) {
				drawSprite(omorimap[9], 90, 0, 1260, 720);

				if(event==147||event==148)
				{
					drawSprite(artemwater, 487,242,40*(27/18f),55);
					drawSprite(basilwater, 905,251,40*(27/18f),55);
				}
				if(event==177)
				{
					drawSprite(crowning, artem.x-(40*(4/18f)),artem.y,87,55);

				}
			}else if(nowroom==omoribdplace) {
				drawSprite(omorimap[1], 90, 0, 1260, 720);
			}

		}

		drawRoom(nowroom);



		if(event==4)
		{
			drawSprite(img_found,sans.x+28-eyeX,sans.y-66-eyeY,90*(12/19f),90*(12/19f));
		}

		if(isBattleStarted)
		drawBattle();
		/*
		float areax=0,areay=0;
		Thing a = Graph.artem;
		if(Graph.artem.w==40)
            {
                switch (Graph.artem.nowanim)
                {
                    case 0://down
                        areax=a.x;
                        areay=a.y+55;
                        break;
                    case 1://up
                        areax=a.x;
                        areay=a.y;
                        break;
                    case 2://left
                        areax=a.x-40;
                        areay=a.y+55/2f;
                        break;
                    case 3://right
                        areax=a.x+40;
                        areay=a.y+55/2f;
                        break;
                }
            }else
            switch (Graph.artem.nowanim)
            {
                case 0://down
                    areax=a.x;
                    areay=a.y+90*2;
                    break;
                case 1://up
                    areax=a.x;
                    areay=a.y;
                    break;
                case 2://left
                    areax=a.x-90;
                    areay=a.y+90;
                    break;
                case 3://right
                    areax=a.x+90;
                    areay=a.y+90;
                    break;
            }
		drawSprite(map[1],areax-eyeX,areay-eyeY,(Graph.artem.w==40?40:90),(Graph.artem.w==40?40:90));

		ArrayList<Thing> objcts = nowroom.objcts;


		for(int i = 0; i<objcts.size();i++)
			if(objcts.get(i)!=artem)
			drawSprite(map[1],objcts.get(i).x-eyeX,objcts.get(i).y+(objcts.get(i).h-90)-eyeY,objcts.get(i).w,90);

		//drawSprite(map[1],areax-eyeX,areay-eyeY,90,90);
		//*/

		if(!blockmove)
		drawAssembly();

		if(nowdialog!=null&&nowdialog.isOpened)
		{
			drawDialog(nowdialog);
			if(nowquestion!=null&&!nowquestion.isReplied)
			drawQuestion(nowquestion);
		}

		batch.setColor(0,0,0,ztm);
		drawSprite(map[0],0,0,1440,720);
		batch.setColor(1,1,1,1);
		//drawSprite(map[1],Mistener.tx,Mistener.ty,Mistener.dx,Mistener.dy);
	//	amogus[0].setSize(Mistener.tx,Mistener.ty,Mistener.dx,Mistener.dy);
	}
	float ztm = 0;

	void drawQuestion(Question q){



		drawString(q.q1,585,563,(Mistener.onScreen&&Mistener.dx<-50)?font_determinationyellow:font_determination,57);

		drawString(q.q2,585+315,563,(Mistener.onScreen&&Mistener.dx>50)?font_determinationyellow:font_determination,57);
	}


	static boolean fightstarted=false;

	void startAttack()
	{
		attack=true;
		atk_bone.x = 1000;
		nowdialog.close();
		nextEvent(61);
	}

	void countBattle()
	{
		if(attack)
		{
			if(atk_bone.x<400) {
				attack = false;
				nextEvent(56);
			}
			//Field.lol(48,320,1343,235);
			Field.lol(546,512-324,344,344);

			float area = 100;

			//if()
			if(Mistener.onScreen) {
					if(Math.abs(Mistener.dx)>=100) {
						if (Mistener.dx > 0)
							Soul.x -= (Soul.x - (720 + area)) / 10;
						else
							Soul.x -= (Soul.x - (720 - area)) / 10;
					}
					else
						Soul.x-=(Soul.x-(720))/10;
					if(Math.abs(Mistener.dy)>=100) {
						if (Mistener.dy < 0)
							Soul.y -= (Soul.y - (360 - area)) / 10;
						else
							Soul.y -= (Soul.y - (360 + area)) / 10;
					}
					else
						Soul.y-=(Soul.y-(360))/10;
			}else
			{
				Soul.x-=(Soul.x-(720))/10;
				Soul.y-=(Soul.y-(360))/10;
			}

			if(invtime<=0) {
				soul.setFrame(0);
				if (Soul.x >= atk_bone.x && Soul.x <= atk_bone.x + atk_bone.w && Soul.y >= atk_bone.y && Soul.y <= atk_bone.y + atk_bone.h) {
					invtime = 1;
					sound_damage.play();
				}
			}
			else {
				invtime-=Gdx.graphics.getDeltaTime();
				soul.update();
			}
			if(event!=70)
			atk_bone.x-=5;


		}else
		{
			Field.lol(48,320,1343,235);
		}


	}

	float invtime = 0;
	Texture blaster;
	void drawBattle()
	{
		drawSprite(map[0],0,0,1440,720);
		if(event==54)
		{
			drawSprite((artem.getAnimation()),artem.x-eyeX,artem.y-eyeY,artem.w,artem.h);


		}

		if(fightstarted)
		{
			for(int i = 0; i<btn_battle.length;i++)
			{
				btn_battle[i].setXYWH(95+331*i,592,260,103);
				drawSprite(btn_battle[i]);
			}

		//	Field.lol(48,320,1343,235);


			float px = 490, py = 20, pw = 347, ph = 409;

			float armshift = (float)(Math.cos(Math.PI/(360*3)*System.currentTimeMillis())*20);
			float bodyshift = (float)(Math.cos(Math.PI/(360*3)*System.currentTimeMillis())*10);

			drawSprite(papyarmback,px,py+armshift,pw,ph);
			if(numattack<=1)
			drawSprite(bodysad,px,py+bodyshift,pw,ph);
			else if(numattack<=3)
				drawSprite(bodypres,px,py+bodyshift,pw,ph);
			else
				drawSprite(bodymad,px,py+bodyshift,pw,ph);
			drawSprite(papyarmfront,px,py+armshift,pw,ph);
			drawSprite(papylegs,px,py,pw,ph);
			drawSprite(papyscarf,px+bodyshift,py,pw,ph);
			papyscarf.update();
			float x=Field.x,y=Field.y,w=Field.w,h=Field.h;

			drawSprite(map[0],x,y,w,h);
			drawSprite(map[1],x,y,w,10);
			drawSprite(map[1],x,y+h,w,10);
			drawSprite(map[1],x,y,10,h);
			drawSprite(map[1],x+w,y,10,h);


			if(attack)
				drawSprite(soul,Soul.x-90*(16/19f)/4,Soul.y-90*(16/19f)/4,90*(16/19f)/2,180*(15/36f)/2);


		}else{
			if((((eventtime>0.04&&eventtime<=0.06)||(eventtime>0.08&&eventtime<=0.09))||event==55))
				drawSprite(soul,Soul.x-90*(16/19f)/4,Soul.y-90*(16/19f)/4,90*(16/19f)/2,180*(15/36f)/2);
		}


		if(attack)
		{
		//	atk_bone.x = Mistener.tx;
		//	atk_bone.y = Mistener.ty;
		//	atk_bone.w = Mistener.dx;
		//	atk_bone.h = Mistener.dy;
			if(event!=70)
			drawSprite(atk_bone.txtr,atk_bone.x,atk_bone.y,atk_bone.w,atk_bone.h);
			else{
				if(gbmn<1) {
					gbmn += 0.05f;
					drawSprite(blaster,337+(797/2f*(1-gbmn)),7+(158/2f*(1-gbmn)),797*gbmn,158*gbmn);
				}else
				{
					drawSprite(blaster,337+(float)(-5+Math.random()*10),7+(float)(-5+Math.random()*10),797*gbmn,158*gbmn);
					addTime();
					if(eventtime>2) {

						System.exit(0);
					}
				}
			}

		}

	}
	float gbmn=0;
	static boolean isBattleStarted=false;

	void drawDialog(Dialog d)
	{

		d.update();
		if(d.omoristyle)
		{
			if (d.letterchanged)
				voice_omori.play();
			float x = 247, y = 447, w = 996, h = 221;
			drawSprite(map[0], x, y, w, h);
			drawSprite(map[1], x, y, w, 3);
			drawSprite(map[1], x, y + h, w, 3);
			drawSprite(map[1], x, y, 3, h);
			drawSprite(map[1], x + w, y, 3, h);
			drawString(d.getStr(), 523 - 235, 480, font_omori, 57);

			if(d.omoface!=null) {
				x = 982;
				y = 186;
				w = 252;
				h = 245;
				drawSprite(map[0], x, y, w, h);
				drawSprite(d.omoface, x, y, w, h);
				drawSprite(map[1], x, y, w, 3);
				drawSprite(map[1], x, y + h, w, 3);
				drawSprite(map[1], x, y, 3, h);
				drawSprite(map[1], x + w, y, 3, h);
			}

			if(d.name!=null) {
				x = 249;
				y = 366;
				w = 266;
				h = 58;
				drawSprite(map[0], x, y, w, h);
				drawSprite(map[1], x, y, w, 3);
				drawSprite(map[1], x, y + h, w, 3);
				drawSprite(map[1], x, y, 3, h);
				drawSprite(map[1], x + w, y, 3, h);
				drawString(d.name, 299,385, font_omori);
			}
		}
		else
		{
			if (d.letterchanged)
				d.voice.play();
			if(fightstarted)
			{
				if(d.font==font_papyrustiny) {
					drawSprite(img_dialbubble, 850, 75, 453, 217);
					drawString(d.getStr(), 935, 104, d.font, 41);
				}
				else
					drawString(d.getStr(), 134,358,d.font,62);
			}
			else {
				float x = 247, y = 447, w = 996, h = 221;
				drawSprite(map[0], x, y, w, h);
				drawSprite(map[1], x, y, w, 10);
				drawSprite(map[1], x, y + h, w, 10);
				drawSprite(map[1], x, y, 10, h);
				drawSprite(map[1], x + w, y, 10, h);


				if (d.face != null) {
					drawSprite(d.face, 318, 493, 148, 148);
					drawString(d.getStr(), 523, 480, d.font, 57);

				} else {

					drawString(d.getStr(), 523 - 235, 480, d.font, 57);

				}
			}
		}


	}

	void count()
	{
		logMistener();
		nowroom.update();
		//System.out.println(event);
		//System.out.println(artem.x+","+artem.y);
		//System.out.println(eyeX+","+eyeY);
		if(!blockmove)
			countMovement();
		countEvent();


		if(isBattleStarted)
			countBattle();

		waitForPosition();
		//graydoor.setSize(Mistener.tx,Mistener.ty,Mistener.dx,Mistener.dy);
	}

	void waitForPosition(){
		if(artem.w==40)
		{
			if(artem.y>720&&nowroom==omoristart)
			{
				nextEvent(122);
			}
			if(nowroom==omoriway) {
				if (artem.y <= -55) {
					nextEvent(124);
				}
				if(artem.y>=720)
				{
					nextEvent(125);
				}
			}
			if(nowroom==omoritobasil)
			{
				if(artem.x>=701&&artem.x<=752&&artem.y<=530&&artem.y>=460)
				{
					nextEvent(127);
				}
				if(artem.y<=-55)
					nextEvent(126);
			}
			if(omoritobasil2==nowroom)
			{
				if(artem.y>720)
					nextEvent(128);
				if(artem.x>=608&artem.x<=666&&artem.y<=196&&artem.y>=185)
					nextEvent(129);
			}
		}
	}

	static boolean blockmove=false;
	static boolean eventJustStarted=true;

	static void nextEvent(int event)
	{
		Graph.event =event;
		eventJustStarted=true;
		eventtime = 0;
	}
	static void nextEvent()
	{
		Graph.event++;
		eventJustStarted=true;
		eventtime = 0;
	}

	void addTime()
	{
		eventtime+=Gdx.graphics.getDeltaTime();
	}

	void countEvent()
	{

		switch (event)
		{
			case 0:
				if(nowdialog!=null)
				nowdialog.isOpened=false;
				blockmove=false;
				eyeX=(int)(artem.x-682);
				eyeY=(int)(artem.y-285);
				if(nowroom==papyroom)
				{

					eyeX=49;
					eyeY=Math.max(eyeY,0);
					eyeY=Math.min(eyeY,487);
					tv.getAnimation().update();
					painting.getAnimation().update();

				}
				if(nowroom==snowdin)
				{
					//artefact.x = Mistener.dx+artem.x;
					//ct.y = Mistener.dy+artem.y;
					//System.out.println((Mistener.dx+artem.x)+"f ,"+(Mistener.dy+artem.y)+'f');

				}

				if(artem.w==40)
				{
					eyeX=0;
					eyeY=0;
				}

				break;
			case 1:
				if(eventJustStarted)
				{


					blockmove=true;
					eventJustStarted=false;
					if(artem.nowanim==1)dooropen.play();

				}
				if(artem.nowanim!=1)
				{
					event=0;
					break;
				}
				graydoor.setFrame(1);

				ztm+=0.05f;
				if(ztm>=1)
				{
					nowroom=snowdin;
					nextEvent(2);
				}

				break;
			case 2:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					artem.anim = anotartem;
					artem.h = 180;
					artem.x = 1546;
					artem.y = 883;
					sans.nowanim = 1;
					eyeX=(int)(artem.x-682);
					eyeY=(int)(artem.y-285);
				}
				sans.x = 1546+158;sans.y = 883-228;
				ztm-=0.05f;
				if(ztm<=0)
					nextEvent(3);
				break;
			case 3:
			//	eventtime+=Gdx.graphics.getDeltaTime();
				if(eyeY<artem.y-285-100)
					nextEvent(4);

					eyeY-=2;
				break;
			case 4:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					sans.nowanim=0;
					sound_enemy.play();
				}
				addTime();
				if(eventtime>1)
					nextEvent(5);
				break;
			case 5:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					mus_sansnomel.play();
					nowdialog = new Dialog("*  о, это ты.",sansface[1],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished)
					nextEvent(6);
				break;

			case 6:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("*  честно говоря, я ожидал\n   увидеть тебя чуть позже.",sansface[1],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished)
					nextEvent(7);

				break;

			case 7:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("*  где то через две минуты.",sansface[0],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished)
					nextEvent(8);

				break;
			case 8:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("*  так что подготовиться\n   я не успел.",sansface[1],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished)
					nextEvent(9);

				break;

			case 9:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("*  дай мне немного времени.",sansface[2],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished) {
					nextEvent(10);
					nowdialog.isOpened=false;
				}

				break;

			case 10:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					sans.nowanim=3;
				}

				sans.getAnimation().update();
				sans.x+=250*Gdx.graphics.getDeltaTime();
				if(sans.x-eyeX>1450)
					nextEvent(11);

				break;

			case 11:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=2;
				}
				addTime();
				if(eventtime>2)
				nextEvent(12);
				break;
			case 12:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					mus_sans.setPosition(mus_sansnomel.getPosition());
					mus_sans.play();
					mus_sansnomel.pause();
				}
				addTime();
				if(eventtime>1)
					nextEvent(13);
				break;
			case 13:
				if(sans.x<=1546+158)nextEvent(14);
				sans.x-=250*Gdx.graphics.getDeltaTime();
				sans.getAnimation().update();
				break;
			case 14:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=0;
					nowdialog = new Dialog("*  готово.",sansface[3],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

				case 15:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=0;
					nowdialog = new Dialog("*  и так, насколько\n   мне известно, ты должен \n   что то найти.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 16:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=0;
					nowdialog = new Dialog("*  ты знаешь, что это?",sansface[1],24,voice_sans,font_sans);
					nowquestion = new Question("Да","Нет",17,21);
				}

				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}

				break;

			case 17:
				if(eventJustStarted)
				{
					eventJustStarted = false;
					nowdialog = new Dialog("*  ок ищи.",sansface[1],24,voice_sans,font_sans);
				}

				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent(0);
				}

				break;

			case 18:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=0;
					nowdialog = new Dialog("*  ждешь чего то?",sansface[3],24,voice_sans,font_sans);
					sans.event++;
					blockmove=true;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent(0);
				}
				break;

			case 19:
				if(eventJustStarted) {
					eventJustStarted = false;
					sans.nowanim=0;
					nowdialog = new Dialog("*  нашел?",sansface[1],24,voice_sans,font_sans);
					nowquestion = new Question("Да","Нет",20,21);
					blockmove=true;
				}


				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}

				break;

			case 20:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  в таком случае\n   можешь идти, наверное",sansface[3],24,voice_sans,font_sans);
					blockmove=true;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent(0);
				}
				break;

			case 21:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  оу.",sansface[0],24,voice_sans,font_sans);
					blockmove=true;
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 22:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  к счастью, у меня\n   как раз есть устройство.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 23:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  для поиска всяких\n   потерянных штук.",sansface[2],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 24:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  пошли, я знаю короткий путь.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent();
				}
				break;

			case 25:

				boolean b1=false,b2=false;
				if(artem.x<sans.x-110)
				{
					artem.x+=200*Gdx.graphics.getDeltaTime();
					artem.nowanim=3;
				}else{
					b2=true;
				}

				if(artem.x>sans.x-100)
				{
					artem.x-=200*Gdx.graphics.getDeltaTime();
					artem.nowanim=2;
					b2=false;
				}else{
					b2=true;
				}

				if(artem.y>sans.y-(artem.h-sans.h))
				{
					artem.y-=200*Gdx.graphics.getDeltaTime();
					artem.nowanim=1;
				}else{
					b1=true;
				}

				if(b1&&b2)
				{
					nextEvent();
				}

				artem.getAnimation().update();

				break;

			case 26:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					sans.nowanim=3;
					artem.nowanim=3;
					ztm=0;
				}
				sans.getAnimation().update();
				artem.getAnimation().update();
				sans.x+=200*Gdx.graphics.getDeltaTime();
				artem.x+=200*Gdx.graphics.getDeltaTime();//66666666666666666666666uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuyyyyuuy
				if(artem.x-eyeX>1440&&sans.x-eyeX>1440)
					ztm+=0.05f;

				if(ztm>=1)
					nextEvent();
				break;

			case 27:
				if(eventJustStarted)
				{
					artem.anim = anotartem;

					artem.h = 180;
					eventJustStarted=false;
					nowroom=papyroom;
					eyeX = 0;
					eyeY = 0;
					artem.x = 1094;
					//artem.y= 977;
					artem.y= 977+90*4;
					sans.x = 1094;
					sans.y = 977+90*3;

					sans.nowanim=1;
					artem.nowanim=1;

					eyeX=(int)(artem.x-682);
					eyeY=(int)(artem.y-285);


						eyeX=49;
						eyeY=Math.max(eyeY,0);
						eyeY=Math.min(eyeY,487);

				}
				if(ztm>0)ztm-=0.05;
				if(ztm<=0)nextEvent();
				break;
			case 28:
				sans.getAnimation().update();
				artem.getAnimation().update();
				if(sans.y>775)
				sans.y-=200*Gdx.graphics.getDeltaTime();
				else
				{
					sans.nowanim=2;
					sans.x-=200*Gdx.graphics.getDeltaTime();
				}
				if(artem.y>775) {
					artem.y -= 200 * Gdx.graphics.getDeltaTime();
					artem.getAnimation().update();
				}
				else
				{
					artem.nowanim=2;
					artem.getAnimation().setFrame(0);
				}

				if(sans.x<774)
					nextEvent();

				break;

			case 29:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  устройство наверху,\n   в комнате брата.",sansface[1],24,voice_sans,font_sans);
					sans.nowanim=3;
					sans.setFrame(0);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 30:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  мы только что прибыли,\n   так что пока оно там.",sansface[0],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 31:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  вот только...",sansface[2],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 32:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  брат неважно себя чувствует\n   в последнее время.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 33:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  и к нему в комнату\n   мы пока не попадем.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent();
				}
				break;

			case 34:
				sans.x-=200*Gdx.graphics.getDeltaTime();
				sans.nowanim=2;
				sans.getAnimation().update();

				if (sans.x<=517)
				{
					sans.nowanim=3;
					nextEvent();
				}
				break;

			case 35:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  так что нам придется\n   подождать.",sansface[1],24,voice_sans,font_sans);
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent();
				}
				break;
			case 36:
				sans.y+=200*Gdx.graphics.getDeltaTime();
				sans.nowanim=1;
				sans.getAnimation().update();
				if(sans.y>=899)
					nextEvent();
				break;
			case 37:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  располагайся.",sansface[3],24,voice_sans,font_sans);
					sofa.setFrame(1);
					sans.x=-999;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					tv.nowanim=1;
					nextEvent(0);
				}
				break;

			case 38:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("УХОДИ. Я НЕ ДОСТОИН.",null,24,voice_papyrus,font_papyrus);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 39:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вызвать Папируса на поединок?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да","Нет",40,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}
				break;

			case 40:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вы сказали Папирусу, что хотите\nсразиться с ним.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 41:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("...\n...\nКТО ТЫ?",null,24,voice_papyrus2,font_papyrus);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 42:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Я НЕ СЛЫШАЛ ТВОЕГО\nГОЛОСА РАНЬШЕ\n...",null,24,voice_papyrus,font_papyrus);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 43:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("УХОДИ. Я СЛИШКОМ СЛАБ.",null,24,voice_papyrus2,font_papyrus);
					papydoor.event=44;
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 44:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вызвать Папируса на поединок?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да","Нет",45,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}
				break;

			case 45:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вы сказали Папирусу, для вас\n это честь - сразиться с ним.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 46:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("ТЫ ВРЕШЬ. ТЫ ПРОСТО\nХОЧЕШЬ ПОДДЕРЖАТЬ МЕНЯ",null,24,voice_papyrus,font_papyrus);
					papydoor.event=44;
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 47:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Я НЕ СМОГУ ПОБЕДИТЬ ТЕБЯ.\nЯ БЕСПОЛЕЗЕН.\nУХОДИ.",null,24,voice_papyrus2,font_papyrus);
					papydoor.event=48;

				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 48:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вызвать Папируса на поединок?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да","Нет",49,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}
				break;

			case 49:
				if(eventJustStarted) {
					blockmove=true;
					eventJustStarted = false;
					nowdialog = new Dialog("Вы сказали Папирусу, что вы здесь\nтолько чтобы сразиться с ним.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 50:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("...\n...\n...",null,3,null,font_papyrus);
					mus_sans.pause();

				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 51:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("ТЫ ТАК СИЛЬНО ХОЧЕШЬ\nНАВРЕДИТЬ МНЕ?",null,10,voice_papyrus,font_papyrus);
					papydoor.event=48;

				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 52:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("...\nЧТО ЖЕ.\n...",null,3,voice_papyrus2,font_papyrus);
					papydoor.event=48;

				}
				if(nowdialog.isFinished)
				{
					nextEvent();

				}
				break;

			case 53:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("МОЖЕТ, БУДЕТ ДАЖЕ\nЛУЧШЕ ЕСЛИ МЕНЯ\nНЕ СТАНЕТ.",papyface[0],24,voice_papyrus,font_papyrus);
					papydoor.setFrame(1);
					papyrus.x=208.439f;
					papyrus.y=90.90141f;
					dooropen.play();

				}
				if(nowdialog.isFinished)
				{
					nowdialog.isOpened=false;
					nextEvent();
				}
				break;
			case 54:
				if(eventJustStarted) {
					eventJustStarted = false;
					battlestart.play();
					isBattleStarted=true;

				}
				Soul.x = -7+artem.x;
				Soul.y = 105+artem.y;
				addTime();
				if(eventtime>0.2)
					nextEvent();
				break;
			case 55:
				Soul.x-=(Soul.x-720)/5;
				Soul.y-=(Soul.y-720/2f)/5;
				if(Math.abs(Soul.x-720)<=0.2f&&Math.abs(Soul.y-720f/2)<=0.2f)
					ztm+=0.05f;
				if(ztm>=1) {
					nextEvent();
					if(!papytheme.isPlaying())
					papytheme.play();
				}
					break;
			case 56:
				if(eventJustStarted) {
					eventJustStarted = false;
					ztm=0;
					fightstarted=true;
					switch((int)(Math.random()*5))
					{
						case 0:
							nowdialog=new Dialog("Вот он: настоящий пельмень!",null,24,voice_mind,font_determination);
							break;
						case 1:
							nowdialog=new Dialog("Спагетти пока не пахнет.",null,24,voice_mind,font_determination);
							break;
						case 2:
							nowdialog=new Dialog("Вы слышите чей то крепкий сон.",null,24,voice_mind,font_determination);
							break;
						case 3:
							nowdialog=new Dialog("Булочка с костями грустит.",null,24,voice_mind,font_determination);
							break;
						case 4:
							nowdialog=new Dialog("(ждет чего то)",null,24,voice_mind,font_determination);
							break;

					}

				}
				break;

			case 57:
				if(eventJustStarted) {
					eventJustStarted = false;

					if (Math.random() > 0.5f)
						Graph.nowdialog = new Dialog("Вы употребили сендвич.\nСендвич сдался без боя.\nВаши 03 полностью восстановлены.", null, 24, Graph.voice_mind, Graph.font_determination);
					else
						Graph.nowdialog = new Dialog("Вы укусили печеньку.\nПеченька укусила в ответ.\nВаши 03 полностью восстановлены.", null, 24, Graph.voice_mind, Graph.font_determination);
					Graph.Soul.hp = Graph.Soul.maxhp;
				}


				if(nowdialog.isFinished)
				{
					startAttack();
					nextEvent(61);
					nowdialog.close();
				}

				break;

			case 58:
				if(eventJustStarted)
				{
					eventJustStarted=false;

							nowdialog = new Dialog("Вы сказали Папирусу, что у него\nсамые рельефные мышцы во всём Подземелье.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
					if (nowdialog.isFinished)
					{
						nextEvent();
					}

				break;

			case 59:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Папирус покраснел.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					startAttack();
					nowdialog.close();
					nowdialog.isOpened=false;
				}

				break;

			case 60:
				if(eventJustStarted)
				{
					startAttack();
					nowdialog.close();
				}
				break;

			case 61:

				break;

			case 62:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Вы говорите Папирусу, что он может стать\nвеликим, если приложит чуть больше усилий.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 63:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Папирус выглядит раздраженным.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{

					startAttack();
				}

				break;

			case 64:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Вы говорите Папирусу, что он недооценивает\nсвои силы.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					nextEvent();
				}

			case 65:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Папирус зол.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					nextEvent();
				}

				break;
				case 66:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("ХВАТИТ.", null, 24, Graph.voice_papyrus2, Graph.font_papyrustiny);

				}
				if (nowdialog.isFinished)
				{
					startAttack();
					prefs.putInteger("event",71);
					prefs.putFloat("artx",artem.x);
					prefs.putFloat("arty",artem.y);
					prefs.flush();
				}

				break;

			case 67:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Вы сказали Папирусу что он самый крутой чувак.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 68:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Папирус в ярости.", null, 24, Graph.voice_mind, Graph.font_determination);

				}
				if (nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 69:
				if(eventJustStarted)
				{
					eventJustStarted=false;

					nowdialog = new Dialog("Я СКАЗАЛ ХВАТИТ!", null, 24, Graph.voice_papyrus2, Graph.font_papyrustiny);

				}
				if (nowdialog.isFinished)
				{
					startAttack();
					nextEvent(70);
					gasblas.play();
				}

			case 70:

				break;

			case 71:
				if(eventJustStarted)
				{
					ztm=1;
					eventJustStarted=false;
					blockmove=true;
				}
				ztm-=0.05f;
				System.out.println(ztm);
				if(ztm<=0)
					nextEvent();
				break;
			case 72:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("О НЕТ, ТЫ В ПОРЯДКЕ?",papyface[1],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 73:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Я НЕ ЗНАЮ, ЧТО\nПРОИЗОШЛО НО\nМОЯ АТАКА ИСЧЕЗЛА",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 74:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Я ДАЖЕ И НЕ ДУМАЛ, \nЧТО Я МОГУ...                         \nТАК!",papyface[1],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;


			case 75:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("СПАСИБО, СТРАННЫЙ \nМОНСТР, ЗА ТВОИ \nСЛОВА!",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 76:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Я БУДУ БОЛЬШЕ\nВЕРИТЬ В СЕБЯ \nИ В ДРУГИХ!",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 77:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("САНС, Я ИДУ К \nАНДАЙН!",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 78:

				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Я СТАНУ ЧЛЕНОМ\nКОРОЛЕВСКОЙ ГВАРДИИ!",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent();
				}
				break;

			case 79://down up left right

				artem.nowanim = 2;
				artem.getAnimation().update();
				artem.x+=100*Gdx.graphics.getDeltaTime();
				if(artem.x> papyrus.x+papyrus.w+20)
					nextEvent(80);
				break;
			case 80://223.18388,651.29626  1051.3004,701.1609  1081.2513,984.37787
				if(eventJustStarted) {
					eventJustStarted = false;
					papydoor.anim=graydoorpap;
					papydoor.setFrame(0);
					papydoor.event = 83;
					doorclose.play();

				}

				papyrus.update();
				papyrus.y+=600*Gdx.graphics.getDeltaTime();
				if(papyrus.y>=651)
					nextEvent();
				break;
			case 81:
				papyrus.nowanim=3;
				papyrus.update();
				papyrus.x+=600*Gdx.graphics.getDeltaTime();
				if(papyrus.x>=1051)
					nextEvent();
				break;

			case 82://223.18388,651.29626  1051.3004,701.1609  1081.2513,984.37787
				papyrus.update();
				papyrus.nowanim=0;
				papyrus.y+=600*Gdx.graphics.getDeltaTime();
				if(papyrus.y>=1200)
					nextEvent(0);
				break;

			case 83:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					dooropen.play();
				}
				papydoor.setFrame(1);
				papydoor.event = 84;
				nextEvent(0);
				break;

			case 84:
				blockmove=true;
				ztm+=0.05f;
				//TODO
				if(ztm>=1)
				nextEvent(121);
				break;

			case 85:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это просто картина.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 86:
				if(eventJustStarted)
				{
					painting.nowanim=1;
					eventJustStarted=false;
					nowdialog = new Dialog("Шутка.",null,24,voice_mind,font_determination);
					painting.event = 98;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 87:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это книга шуток.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 88:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Заглянуть внутрь?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да","Нет",89,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}
				break;
			case 89:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Вы открыли книгу.\nВнутри изображен молодой\nмужчина.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 90:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Чуть ниже есть надпись.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(99);
				}
				break;
			case 91:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Never gonna give you up                   \nNever gonna let you down",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 92:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Вы закрыли книгу.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();
				}
				break;
			case 93:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это дверь в комнату Санса.\nОна открыта. Вы можете зайти\nв любой момент.",null,24,voice_mind,font_determination);
					sansdoor.event++;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 94:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Нет никаких преград,\nдля прохождения внутрь.",null,24,voice_mind,font_determination);
					sansdoor.event++;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 95:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("эй, зачем ты смотришь\nна эту дверь?",sansface[0],24,voice_sans,font_sans);
					sansdoor.event+=2;
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 96:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("просто зайди внутрь.",sansface[3],24,voice_sans,font_sans);
					if(sofa.event==104)sofa.event=105;
				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 97:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это дверь.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;

			case 98:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Вас залансерили.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;

			case 99:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Почесть надпись?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да","Нет",91,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
				}

				break;

			case 100:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("о да, моё любимое шоу.",sansface[3],24,voice_sans,font_sans);

				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;

			case 101:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("О да, ваше любимое шоу.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 102:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Присыпка на камне гласит:\n",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 103:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("\"ССПС\"",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;

			case 104:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Санс заснул.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();
				}
				break;
			case 105:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Санс заснул. \nСтранно, он ведь только что говорил.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);

					nowdialog.close();
				}
				break;

			case 106:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это коробка, наполненная вещами.\nНа ней написано \"Папирус.\"",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);

					nowdialog.close();
				}
				break;
			case 107:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это коробка, полная клоунских носов.\n На ней написано \"Санс\".",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);

					nowdialog.close();
				}
				break;
			case 108:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это говорящая игрушка в виде\nРика Эстли.",null,24,voice_mind,font_determination);

				}
				if(nowdialog.isFinished)
				{
					nextEvent();

				}
				break;
			case 109:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Нажать на неё?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Да", "Нет",110,0);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);

				}
				break;
			case 110:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("- ъеъ",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();

				}
				break;

			case 111:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это не то, что вы ожидали услышать.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();

				}
				break;

			case 112:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это музыкальные яйца.\nОни напевают очень знакомую мелодию.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();
				}
				break;

			case 113:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Это древний артефакт.\nРядом ни одной собаки.\n",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();

				}
				break;
			case 114:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Взять артефакт с собой?",null,24,voice_mind,font_determination);
					nowquestion = new Question("Нет.","НЕТ!!",115,115);
				}
				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);

				}
				break;

			case 115:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Какая жалость.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();

				}
				break;

				case 116:
					if(eventJustStarted)
					{
						blockmove=true;
						eventJustStarted=false;
						nowdialog = new Dialog("Это мячик. На нём написано\n\"Кел был здесь!\"",null,24,voice_mind,font_determination);
					}
					if(nowdialog.isFinished)
					{

						nextEvent(0);
						nowdialog.close();

					}
					break;

			case 117:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Вы нашли его!",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent();

				}
				break;
			case 118:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("А, нет, подарок адресован Артёму.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();

				}
				break;

			case 119:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Мусор побольше.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();

				}
				break;

			case 120:
				if(eventJustStarted)
				{
					blockmove=true;
					eventJustStarted=false;
					nowdialog = new Dialog("Мусор поменьше.",null,24,voice_mind,font_determination);
				}
				if(nowdialog.isFinished)
				{
					nextEvent(0);
					nowdialog.close();

				}
				break;

			case 121:
				if(eventJustStarted)
				{
					eventJustStarted=false;
						if(!mus_trees.isPlaying())
							mus_trees.play();

					artem.anim = omoriartem;
					nowroom = omoristart;
					eyeX = 0;
					eyeY = 0;
					artem.x = 720;
					artem.y = 260;
					artem.w = 40;
					artem.h = 55;
				}
				ztm-=0.05f;
				if(ztm<=0)
					nextEvent(0);
				break;
			case 122:

				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent();
					artem.x =  694.41016f; artem.y = 14.489032f;
					blockmove=false;
					nowroom = omoriway;

				}
				break;
			case 123:

				ztm-=0.05f;
				if(ztm<=0)
					nextEvent(0);
				break;

			case 124:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(123);
					artem.x =  699.41016f; artem.y = 673;
					nowroom=omoristart;
				}
				break;
			case 125:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(123);
					artem.x =  771; artem.y = 14.489032f;
					nowroom=omoritobasil;
				}
				break;
			case 126:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(123);
					artem.x =  694.41016f; artem.y = 720-55;
					nowroom=omoriway;
				}
				break;
			case 127:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(123);
					artem.x =  711; artem.y = 655;
					nowroom=omoritobasil2;
				}
				break;

			case 128:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(123);
					artem.x =  (701+752)/2f ; artem.y = 535;
					nowroom=omoritobasil;
				}
				break;

			case 129:
				blockmove=true;
				ztm+=0.05f;
				if(ztm>=1) {
					nextEvent(130);
					artem.x =  701 ; artem.y = 440;
					nowroom=omoribasil;
				}
				break;

			case 130:
				ztm-=0.05f;
				if(ztm<=0)
					nextEvent(133);
				break;

			case 131:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					blockmove=true;
					nowdialog= new Dialog("Подозрительная личность преграждает путь.",null,20,null);
				}

				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;
			case 132:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					blockmove=true;
					nowdialog= new Dialog("Не подозрительная личность путь не преграждает.",null,20,null);
				}
				if(nowdialog.isFinished)
				{
					nowdialog.close();
					nextEvent(0);
				}
				break;

			case 133:
				addTime();
				if(eventtime>=1)
					nextEvent();
				break;

			case 134:
				artem.y-=100*Gdx.graphics.getDeltaTime();
				artem.nowanim = 1;
				artem.update();
				if(artem.y<300)
					nextEvent();
				break;
			case 135:
				basil.nowanim=3;
				artem.setFrame(0);
				addTime();
				if(eventtime>=1f)nextEvent();
				break;
			case 136:
				basil.nowanim=0;
				addTime();
				if(eventtime>=1f)nextEvent();
				break;
			case 137:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("О... Ты здесь! \nРад видеть тебя, мне говорили, что ты придешь!",basilface[0],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}

				break;
			case 138:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Кто мне об этом сказал?",basilface[6],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 139:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Сейчас это не важно.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}

				break;

			case 140:
				basil.nowanim=2;
				addTime();

				if(eventtime>=1f)
				{
					nextEvent();
				}

				break;
			case 141:
				basil.nowanim=3;
				addTime();

				if(eventtime>=1f)
				{
					nextEvent();
				}

				break;
			case 142:
				basil.nowanim=0;
				basil.update();
				basil.y+=100*Gdx.graphics.getDeltaTime();

				if(basil.y>artem.y-60)
				{
					nextEvent();
				}

				break;

			case 143:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.setFrame(0);
					nowdialog=new Dialog("Слушай... Не хочу показаться навязчивым, но\nне мог бы ты мне помочь?",basilface[6],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 144:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Мне нужно... Полить цветы, да.",basilface[1],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}

				break;

			case 145:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Поможешь мне?",basilface[2],20,"Бэзил");
					nowquestion = new Question("Да","Нет",146,151);
				}

				if(nowquestion.isReplied)
				{
					nextEvent(nowquestion.eventchoosen);
					nowdialog.close();
				}

				break;
			case 146:
				ztm+=0.05f;
				if(ztm>=1)nextEvent();
				break;
			case 147:
				if(ztm>0)
				ztm-=0.005f;


				break;
			case 148:
				ztm+=0.005f;
				if(ztm>=1)nextEvent(149);

				break;

			case 149:
				ztm-=0.05f;
				if(ztm<=0)nextEvent();
				break;

			case 150:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Спасибо большое! Ты очень помог.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent(152);
					nowdialog.close();
				}

				break;

			case 151:
				if(eventJustStarted){
					eventJustStarted=false;
					nowdialog=new Dialog("Ох, ничего страшного! Я и сам справлюсь.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}

				break;

			case 152:
				basil.nowanim=1;
				addTime();
				if(eventtime>2)
					nextEvent();
				break;
			case 153:
				basil.update();
				basil.y-=100*Gdx.graphics.getDeltaTime();
				if(basil.y<153)nextEvent();
				break;
			case 154:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.setFrame(0);
					nowdialog=new Dialog("Знаешь... меня столько всего беспокоит.",basilface[4],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 155:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.setFrame(0);
					nowdialog=new Dialog("Мне не по себе, но... Я слышал, ты очень понимающий.",basilface[3],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;
			case 160:
			case 162:
			case 168:
			case 156:

				addTime();
				if(eventtime>3)
					nextEvent();
				break;
			case 157:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Я не знаю, чего ждать завтра. Не знаю, кем люди \nсчитают меня.",basilface[4],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 158:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Не знаю... кто я вообще такой...",basilface[5],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 159:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Какую ценность я несу...",basilface[5],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;
				case 161:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=1;
					nowdialog=new Dialog("Прости, это очень личное. Но мне кажется,\nты можешь меня понять.",basilface[1],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;
				case 163:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Извини, если прозвучу странно, но ты прямо\nизлучаешь уверенность, заражаешь ею меня",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 164:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Спасибо, что выслушал. Я вижу, что ты человек,\nна которого можно положиться.",basilface[0],20,"Бэзил");
				}

				if(basil.y<=artem.y-60) {
					basil.update();

					basil.y += 100 * Gdx.graphics.getDeltaTime();
				}else
				{
					basil.setFrame(0);
				}


				if(nowdialog.isFinished&&basil.y>artem.y-60)
				{
					nextEvent();
					basil.setFrame(0);
				}
				break;

			case 165:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Думаю, все эти переживания...\n...все страхи...\n...вся паранойя...",basilface[1],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 166:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Это всё не так важно, когда есть кому довериться.\nКогда знаешь, что ты сильнее, чем кажешься\nсамому себе.",basilface[1],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 167:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Ты понимаешь, о чём я?",basilface[0],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;
				case 169:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Спасибо тебе, правда. Ты чудесный.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
				case 170:
				if(eventJustStarted){
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Мы с тобой даже в чём-то похожи.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;

			case 171:
				addTime();
				basil.nowanim=2;
				if(eventtime>1.5f)
					nextEvent();
				break;
			case 172:
				addTime();
				basil.nowanim=3;
				if(eventtime>1.5f)
					nextEvent();
				break;

			case 173:
				if(eventJustStarted){

					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("О! Кажется, уже время. Пойдём.",basilface[1],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 174:
				if(eventJustStarted){

					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("О, ещё кое-что",basilface[0],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;
			case 175:
				basil.nowanim=3;
				basil.update();
				basil.x+=Gdx.graphics.getDeltaTime()*100;
				if(basil.x>=artem.x+55)
					nextEvent();
				break;
			case 176:
				basil.nowanim=0;
				basil.update();
				basil.y+=Gdx.graphics.getDeltaTime()*100;
				if(basil.y>=artem.y)
					nextEvent();
				break;
			case 177:
				crowning.update();
				artem.anim = omoriartemcrown;
				addTime();
				if(eventtime>=3)
					nextEvent();
				break;
			case 178:
				if(eventJustStarted){
					artem.nowanim=3;
					basil.nowanim=2;
					eventJustStarted=false;
					basil.nowanim=0;
					nowdialog=new Dialog("Мне кажется, ты очень мило будешь в этом смотреться.",basilface[2],20,"Бэзил");
				}

				if(nowdialog.isFinished)
				{
					ztm+=0.025f;
					if(ztm>=1)nextEvent();

				}
				break;
			case 179:
				if(eventJustStarted)
				{
					eventJustStarted=false;
					mus_trees.stop();
					mus_bd.play();
					amogus[3].setSize(995,50,97,116);
					papyrus.nowanim=0;
					sans.nowanim=0;
					papyrus.anim = omoripap;
					sans.anim = omorisans;
					papyrus.setSize(0,0,40*(24/18f),40*(43/18f));
					sans.setSize(0,0,40*(19/18f),40*(30/18f));

					artem.anim = omoriartemcrown;
					artem.nowanim = 1;
					nowroom = omoristart;
					eyeX = 0;
					eyeY = 0;
					artem.x = 720;
					artem.y = 260;
					artem.w = 40;
					artem.h = 55;
					nowdialog=new Dialog("abjks",null,2,null);
					sans.nowanim=1;
					papyrus.nowanim = 1;
					sammich.nowanim=1;
					papyrus.x = 589; papyrus.y = 341;
					sans.x = 645; sans.y = 343;
					artem.y = 720+55;
					basil.y = 720;
					artem.x = sammich.x;
					blockmove=true;
					basil.x = artem.x+55;
				}
				nowdialog.close();//YOUR EYES YOU'LL BE HERE SOON
				nowroom = omoribdplace;
				ztm-=0.025f;
				if(ztm<=0)
					nextEvent(180);

				break;
			case 180:

				basil.y-=Gdx.graphics.getDeltaTime()*100;
				artem.y-=Gdx.graphics.getDeltaTime()*100;
				artem.update();
				basil.update();
				if(basil.y<=sammich.y+100)
				{
					nextEvent();
				}

				break;
			case 181:
				artem.setFrame(0);
				basil.setFrame(0);
				if(eventJustStarted)
				{
					eventJustStarted=false;
					nowdialog = new Dialog("Сюрприз!",null,20,"Все");
				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;
			case 182:
				nextEvent();
				break;

			case 183:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("ТАК ВОТ, КТО ВИНОВНИК \nТОРЖЕСТВА!Т Ы ВЫГЛЯДИШЬ ОЧЕНЬ ЗНАКОМО!\nСАНС, ТЫ ЕГО ЗНАЕШЬ?",papyface[2],24,voice_papyrus,font_papyrus);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 184:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("*  впервые вижу.",sansface[3],24,voice_sans,font_sans);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 185:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Надеюсь, тебе понравился сюрприз!",sammichface[0],20,"Сэммич");


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 186:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("Гостей должно было прийти больше,\nно мы не успели разослать приглашения.",sammichface[1],20,"Сэммич");


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 187:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("(печенька всем своим видом поздравляет тебя\nс днем рождения.)",cux,20,null);


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
				}
				break;

			case 188:
				if(eventJustStarted) {
					eventJustStarted = false;
					nowdialog = new Dialog("С праздником!",basilface[2],20,"Бэзил");


				}
				if(nowdialog.isFinished)
				{
					nextEvent();
					nowdialog.close();
				}
				break;

			case 189:
				basil.y+=Gdx.graphics.getDeltaTime()*100;
				basil.nowanim=0;
				basil.update();
				if(basil.y>=artem.x+100)
				{
					nextEvent();
				}
				break;

			case 190:
				artem.nowanim = 0;
				basil.nowanim = 1;
				basil.setFrame(0);
				addTime();
				if(eventtime>=3)
				{
					photo.play();
					nextEvent();
					ztm=0;
				}
				break;

			case 191:
				if(ztm<1)
				ztm+=0.05f;

				break;


		}






	}
	Sound photo;
	float photoy = 0;
	Texture img_photo;
	Texture[] sammichface;
	Texture cux;
	Animation crowning;
	static boolean attack=false;
	static int numattack=0;
static float eventtime = 0;
	void countMovement()
	{
		if(joy.isPushed())
		{
			float x =Mistener.mxa[0]/mnX-(53+354/2f),y = Mistener.mya[0]/mnY-(336+354/2f);
			float resx,resy;
			if(Math.abs(x)>Math.abs(y))
			{
				resx = x/Math.abs(x);
				resy = y/Math.abs(x);

				if(resx>0)artem.nowanim=3; else artem.nowanim=2;

			}else{

				resx = x/Math.abs(y);
				resy = y/Math.abs(y);
				if(resy>0)artem.nowanim=0; else artem.nowanim=1;
			}
			//System.out.println(resx+","+resy);
			Thing a = artem;
			float speed=a.w==40?200:400;
			speed*=Gdx.graphics.getDeltaTime();
			resx*=speed;
			resy*=speed;

			if(!nowroom.get((int)((a.x+resx)/90f),(int)((a.y+90)/90))&&!nowroom.get((int)(((a.x+90)+resx)/90f),(int)((a.y+90)/90))&&!nowroom.get((int)((a.x+resx)/90f),(int)((a.y+180)/90))&&!nowroom.get((int)(((a.x+90)+resx)/90f),(int)((a.y+180)/90)))
				if(nowroom.notIntersect(a.x+resx,a.y+(a.w==40?55/2f:90),(a.w==40?40:90),(a.w==40?55/2f:90)))
				a.x+=resx;
			if(!nowroom.get((int)((a.x)/90f),(int)(((a.y+90)+resy)/90))&&!nowroom.get((int)((a.x+90)/90f),(int)(((a.y+90)+resy)/90))&&!nowroom.get((int)((a.x)/90f),(int)(((a.y+180)+resy)/90))&&!nowroom.get((int)((a.x+90)/90f),(int)(((a.y+180)+resy)/90)))
				if(nowroom.notIntersect(a.x,a.y+(a.w==40?55/2f:90)+resy,(a.w==40?40:90),(a.w==40?55/2f:90)))
				a.y+=resy;

				int b=  ((int)(a.getAnimation().fps*a.getAnimation().time))%2+1;
			a.getAnimation().update();


			if(artem.h==180*(48/36f))
			{
				if(b==((int)(a.getAnimation().fps*a.getAnimation().time))%2)splash.play();
			}



		}else {
			artem.getAnimation().time=0;
		}

		switch (nowroom.id)
		{
			case 0:
				if(artem.x<0)
				{
					artem.x+=720*4;
				}
				if(artem.x>720*4+1)
				{
					artem.x=0;
				}

				if(artem.y>720){
					artem.y=-720;
				}
				if(artem.y<-720)
				{
					artem.y=720;
				}
				break;
		}

	}

	void drawAssembly() {

		drawSprite(joy);


		if (joy.isPushed())
		{
			float x = Mistener.mxa[0]/mnX-84f/2,y = Mistener.mya[0]/mnY-84/2f;
			if(dist2(Mistener.mxa[0]/mnX,Mistener.mya[0]/mnY,53+354/2f,336+354/2f)<=(354/2f)*(354/2f))
			drawSprite(stick,x,y,84,84);
			else
			{
				float resx=(Mistener.mxa[0]/mnX-(53+(354/2f))),resy=(Mistener.mya[0]/mnY-(336+(354/2f)));


				System.out.println(resx+","+resy);
				drawSprite(stick,53+(354/2f)+(resx)-(84/2f),336+(354/2f)+(resy)-(84/2f),84,84);

			}
		}
		else
		{//53,336,354
			drawSprite(stick,53+354/2f-84f/2,336+354/2f-84/2f,84,84);

		}


		drawSprite(btn_Z);
	}
	double dist2(double x,double y,double x1, double y1)
	{
		return ((x-x1)*(x-x1)+(y-y1)*(y-y1));
	}
	public void drawRoom(Room r)
	{
		float w=90,h=90;
		Animation[][] matrix = r.bgmatrix;

		for(int i = 0; i<matrix.length;i++)
			for(int j = 0; j<matrix[i].length;j++) {
				if((!((nowroom==snowdin)&&matrix[i][j]==snowdinmap[0])))
					if(matrix[i][j]!=map[0])
				drawSprite(matrix[i][j], i * w - eyeX, j * h - eyeY, w, h);
			}
			if(event!=147&&event!=148&&event!=177)
			for(int i = 0; i<r.objcts.size();i++){
				Thing t =r.objcts.get(i);
				drawSprite(t.anim[t.nowanim],t.x-eyeX,t.y-eyeY,t.w,t.h);
			}


	}

	@Override
	public void dispose () {

	}


	static int eyeX=0;
	static int eyeY=0;

	void drawSprite(Texture img, float x, float y,float w, float h){
		if(x+w>=0&&x<=1440&&y+h>=0&&y<=720)
			batch.draw(img, x*mnX, (720-y-h)*mnY,w*mnX,h*mnY);
	}

	void drawSprite(Animation img, float x, float y,float w, float h){
		batch.draw(img.getFrame(), x*mnX, (720-y-h)*mnY,w*mnX,h*mnY);
	}



	void drawSprite(Button but){
		if(but.isPushed())
			batch.draw(but.getTexture(), (but.getX()+ but.getDepth())*mnX,(720-but.getH()-but.getY()-but.getDepth())*mnY,(but.getW()-but.getDepth()*2)*mnX,(but.getH()-but.getDepth()*2)*mnY);
		else
			batch.draw(but.getTexture(), but.getX()*mnX,(720-but.getH()-but.getY())*mnY,but.getW()*mnX,but.getH()*mnY);
	}




	private BitmapFont generateFont(String fontName, float sizeX, float sizeY) {

		String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡¿?\"'+-*/()[]={}<>!"+
				"abcdefghijklmnopqrstuvwxyz" +
				"ABCDEFGHIJGKLMNOPQURSTUVWXYZ";


		// Configure font parameters
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters =RUSSIAN_CHARACTERS;
		parameter.size = 24;

		// Generate font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(fontName) );
		BitmapFont font = generator.generateFont(parameter);
		font.getData().setScale(sizeX*mnX,sizeY*mnY);

		// Dispose resources
		generator.dispose();

		return font;
	}

	private BitmapFont generateFont(String fontName, float sizeX, float sizeY, Color color) {

		String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡¿?\"'+-*/()[]={}<>!"+
				"abcdefghijklmnopqrstuvwxyz" +
				"ABCDEFGHIJGKLMNOPQURSTUVWXYZ";


		// Configure font parameters
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters =RUSSIAN_CHARACTERS;
		parameter.size = (int)(24*2.3);

		// Generate font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(fontName) );
		BitmapFont font = generator.generateFont(parameter);
		font.getData().setScale(sizeX*mnX,sizeY*mnY);
		font.setColor(color);
		// Dispose resources
		generator.dispose();

		return font;
	}

	private BitmapFont generateFont(String fontName, float sizeX, float sizeY, Color color,float borderWidth, Color bordercolor) {

		String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡¿?\"'+-*/()[]={}<>!"+
				"abcdefghijklmnopqrstuvwxyz" +
				"ABCDEFGHIJGKLMNOPQURSTUVWXYZ";


		// Configure font parameters
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters =RUSSIAN_CHARACTERS;
		parameter.size = 24;
		parameter.borderColor = bordercolor;
		parameter.borderWidth = borderWidth;
		// Generate font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(fontName) );
		BitmapFont font = generator.generateFont(parameter);
		font.getData().setScale(sizeX*mnX,sizeY*mnY);
		font.setColor(color);
		// Dispose resources
		generator.dispose();

		return font;
	}

	void delay(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	String logMistener()
	{
		System.out.println(Mistener.tx+","+Mistener.ty+","+Mistener.dx+","+Mistener.dy);
		return Mistener.tx+","+Mistener.ty+","+Mistener.dx+","+Mistener.dy;
	}

	void logMistenerEye()
	{
		System.out.println((Mistener.tx+eyeX)+","+(Mistener.ty+eyeY)+","+Mistener.dx+","+Mistener.dy);
	}

	public void drawString(String str, float x, float y, BitmapFont font)
	{
		font.draw(batch,str,x*mnX,(720f-y)*mnY);
	}

	public void drawString(String str, float x, float y, BitmapFont font, float h)
	{
		String[] s = str.split("\n");
		for(int i = 0; i<s.length;i++)
		font.draw(batch,s[i],x*mnX,(720f-(y+h*i))*mnY);
	}

	void log()
	{
		System.out.println("DEBUG!!");
	}

	void log(int i)
	{
		System.out.println(i);
	}

	void log(String str)
	{
		System.out.println(str);
	}

	void log(boolean bool)
	{
		System.out.println(bool);
	}

	void log(char c)
	{
		System.out.println(c);
	}

	void log(float f)
	{
		System.out.println(f);
	}



}
