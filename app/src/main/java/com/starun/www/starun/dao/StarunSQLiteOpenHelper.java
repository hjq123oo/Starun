package com.starun.www.starun.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.R;

/**
 * Created by hjq on 2016/4/6.
 */
public class StarunSQLiteOpenHelper extends SQLiteOpenHelper {


    public StarunSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StarunSQLiteOpenHelper(Context context) {
        super(context, "starun.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"warm_up\" (\n" +
                "\"warm_up_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"title\"  TEXT,\n" +
                "\"gist\"  TEXT,\n" +
                "\"img_id\"  INTEGER,\n" +
                "\"sound_id\"  INTEGER\n" +
                ");");

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题1", "要领1", R.drawable.warm_up_1, null
                });

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题2", "要领2", R.drawable.warm_up_2, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题3", "要领3", R.drawable.warm_up_3, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题4", "要领4", R.drawable.warm_up_4, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题5", "要领5", R.drawable.warm_up_5, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题6", "要领6", R.drawable.warm_up_6, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题7", "要领7", R.drawable.warm_up_7, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题8", "要领8", R.drawable.warm_up_8, null
                });

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题9", "要领9", R.drawable.warm_up_9, null
                });


        db.execSQL("CREATE TABLE \"run_plan\" (\n" +
                "\"run_plan_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"title\"  TEXT,\n" +
                "\"week_index\"  INTEGER DEFAULT 0,\n" +
                "\"option_index\"  INTEGER DEFAULT 0,\n" +
                "\"option_name\"  TEXT,\n" +
                "\"lesson_one\"  TEXT,\n" +
                "\"lesson_one_plan\"  TEXT,\n" +
                "\"lesson_two\"  TEXT,\n" +
                "\"lesson_two_plan\"  TEXT,\n" +
                "\"lesson_three\"  TEXT,\n" +
                "\"lesson_three_plan\"  TEXT,\n" +
                "\"desc\"  TEXT\n" +
                ");");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (1, '计划简介', 0, 0, null, null, null, null, null, null, null, '一旦你准备开始一项锻炼计划，就需要记住3个锻炼原则：适度、一致和休息。这些原则非常简单，如果让它们伴随着你的生活，你会发现，选择过积极的生活将会比选择那种慵懒的经久不动的生活要愉快得多。你也将走向一条很长的不会受伤的路，让你少走数月甚至数年的弯路。\n" +
                "当然，让这3个原则伴随着你的生活并不一定会让你完全不受伤或感到疼痛。但是，这些原则可以帮助你更容易达到健身方面更高的层次，这是通过让你的身体担负适量的压力来实现的。 \n" +
                "\n" +
                "原则1：适度\n" +
                "慢慢开始。甚至在你已经通过别的运动令心血管健康水平良好的情况下，你也应该采取这个建议。或许你能够完成环法自行车赛或者能游泳穿越英吉利海峡，但是这些经历并不能让你成为跑步者。即使是有经验的跑步者（和行走者）也需要注意避免过量的运动。那是因为特殊的肌肉骨骼应力的存在，而它是跑步这项运动所特有的。\n" +
                "人们一般认为心血管系统比肌肉骨骼系统更强壮。在合理的压力下，心血管系统会立即作出响应，它会迅速强化，让你有能力运输更多的氧气到那些缺氧的肌肉中。不幸的是，你的骨骼、韧带、肌腱和肌肉并不能相应地作出调整。开普敦大学锻炼科学和体育医疗研究部主任，《跑步受伤》（Running Injuries）这本书的著者之一蒂姆·诺克斯说：“如果你合理运动，那么经过大概六个月的训练后，你从技术上已经能够跑一个马拉松了，但是你的骨骼还不能承受这样的强度。”他说那些以前并不积极锻炼的人如果一直强忍着坚持训练的话，他们中的大部分在开始的3～6个月会非常容易遭遇应力性骨折。换句话说，你的心肺可能会催促你继续跑下去，但是你的骨骼、韧带、肌腱和肌肉却想让你缓和下来。\n" +
                "很多怀有良好意愿的人训练过度，偏离了他们的训练计划。他们中许多人制订了新年方案以保持形体，并在新年的前几周挤满了健身中心，但是很多人在春天来临的时候退却了。那些没有受伤的人也会为自己太拘泥于自己的训练方案而变得气馁。\n" +
                "尽管人体能够承受相当多的压力，但是你必须慢慢地施压以避免受伤。这也是为什么我们建议你不要在施行本书中的训练计划时往前跳跃，尽管一开始它看上去对你来说可能有点小儿科。跳过训练计划不会让你更快地强壮起来，相反还会增加一些风险，比如肌肉和关节酸疼，或者引发更糟糕的状况。\n" +
                "\n" +
                "原则2：一致\n" +
                "如果说适度是锻炼的第一原则的话，那么一致就是第二原则。违反原则1的人常常会违反原则2。\n" +
                "下面举一个实例。你决定要为了健美的体形而锻炼，因此你走进健身房，或者去跑一段步，运动中你竭尽所能，但接下来的一周你觉得自己像被一辆卡车碾过一样。在完全恢复到能进行下一段训练之前，你又强迫自己开始训练以弥补失去的时间。这种训练根本不是训练。它给你带来的更多是伤害而不是好处，因为它让你感觉更糟糕而不是更美好，很快，平日的感觉袭来，逐渐破坏了你的计划。最终你放弃了训练。\n" +
                "一致性这个优点怎么夸张都不过分。当你的训练保持一致的时候，你的身体会有更多的时间来适应训练的强度。更重要的是，如果你保持一致，就没有必要去弥补失去的时间。一两天额外的艰苦训练不能弥补那些错过的训练课程。实际上，你更可能让你的身体处于过高强度的压力之下，你会发现自己又回到了原点——或者更糟糕，生病或者受伤。\n" +
                "同时，花更多的时间去建立一个坚固的健身基础，你的身体就会更有安全保障，这就意味着你可以随时休息一下，而不会破坏整个训练计划。\n" +
                "如果你认真思考原则1和原则2，就会很容易明白为什么健身的人把训练当成他们生活中的一部分。这种训练无止境的想法可能一开始看上去让人畏惧，特别是在你发现自己第一次努力就非常困难的情况下。但是一旦你的身体和意识开始从训练中受益，你就会发现自己渴求训练。与强迫自己去训练不同，你会为什么时候才有机会再去训练而担忧。如果健身的人在某天急切地想要系上鞋带去跑步，那么他们通常达到了一定境界。\n" +
                "\n" +
                "原则3：给身体休息的时间\n" +
                "休息会给你的身体时间和能量去适应你训练量的一些变化。一旦你的身体适应了，你会变得更强壮、更有效率。计划好休息和训练后恢复的时间，确保把每周的训练内容贯穿在一周的每一天中，而不要短短几天内就完成。以对待训练课程的方式来对待休息，将它当成一种对你的训练计划和幸福感非常重要的有意识的体力活动。休息并不是避免运动这么简单；它是一个能够让你的身体从疲劳中恢复过来的合理周期。\n" +
                "跑步和健身的一个益处就是能够很好地调节你的免疫系统，它会帮助你的身体更好地打败入侵的细菌、病毒和毒素。\n" +
                "\n" +
                "13周训练计划\n" +
                "这个运动医学跑步行走计划每周有3次训练课，每次课持续时间为28~76分钟，该计划已经被很多人谨慎地测试过了。你需要把这3次训练课均匀分配到一周中并尝试制订一个固定的日程安排，这样做是非常重要的。\n" +
                "该计划是逐步执行的，里面包含有很多的行走活动。你可以借助运动手表安排训练课中跑步行走区段的时间。如果你发现进程太慢，尝试着忍受一下，而不要急于跳到下一步，那样做不会让你身体更强健，而只会增加受伤的风险。该计划中最与众不同之处在于跑步行走选项部分，它可以让那些在最开始6周内训练非常吃力的人采取一种更循序渐进的方法来完成这个计划。在这种情况下你仍然要准备完成10公里区段的训练，但是训练是穿插着跑步和行走的，而不是一直在跑步。你训练中的“跑步”部分应该是非常慢的慢跑，要一直处于很舒适的状态。你应该在行走和跑步的过程中都感觉很轻健，要能够进行交谈，能够一次说两三句话而不会喘不过气。最开始，毫无疑问，你会很兴奋，甚至会对训练急不可待，这样可能会让你跑得比理想状态要快。要小心你的速度：因为冲击力会随着步子的变大而增加，这样可能会导致受伤。\n" +
                "还需要记住的是：跑步不是件容易的事。肌肉、肌腱、骨骼和韧带需要时间来适应冲击。如果你遵循这个进程，不多做也不少做，你会诧异它竟然如此简单。最后要记住，锻炼时间也包括5分钟热身和5分钟的训练后放松时间。在你作日程安排时要确保它们成为你训练中的重要环节。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (2, '第1周：步伐', 1, 0, null, '跑步1分钟。行走2分钟。共做8次。', 'run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;', '跑步1分钟。行走2分钟。共做6次。', 'run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;', '跑步1分钟。行走2分钟。共做7次。', 'run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;', '为了让自己的跑步步伐变得轻缓，尝试“拖着脚慢跑”技巧——挺起胸，手臂摆动范围小一些，用小碎步跑，而不要抬高膝关节，尽量不要跳起：这样就比较容易拖着脚。（想象一个正在做简短而有力的拳击动作和快速灵活的踢踏动作的拳击手，或者一个跳恰恰舞的舞蹈家。）把重心放在脚掌的中前部，不要做走路那种明显的脚跟到脚趾的动作。从行走换到跑步要过渡得非常平稳，这样你的身体和意识几乎感觉不到它们的差别，反过来也是这样。\n" +
                "提示：对跑步者来说，最重要的装备是一双合适的好跑鞋。当然还有衣服，特别是贴身的那层，它们一般由化纤面料制成，可以把湿气从身体上排走。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (3, '第2周：建立基础', 2, 0, null, '跑步2分钟。行走2分钟。共做7次。', 'run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;', '跑步1分钟。行走2分钟。共做7次。', 'run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;run,1;walk,2;', '跑步2分钟。行走2分钟。共做6次。', 'run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;', '你可能发现上周1分钟的“拖着脚慢跑”太容易了。如果你保持这种缓慢而轻盈的步伐，可能会对这种不花什么力气的锻炼感觉有点儿失落。这周，尝试着完成几组2分钟的跑步，以让自己重温这种步伐轻盈放松的感觉。要记住上周给你介绍的“拖着脚慢跑”技巧。\n" +
                "提示：非常重要的一点是，每周要找时间进行3次训练。这个跑步行走计划是按周来安排的，为了取得成功，你需要做功课。如果你因为一些原因而不能完成当周的训练，最好在下一周重复这周的课程，然后再继续。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (4, '第3周：增加跑步的时间', 3, 0, null, '跑步3分钟。行走2分钟。共做7次。', 'run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;', '跑步2分钟。行走2分钟。共做6次。', 'run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;', '跑步3分钟。行走2分钟。共做6次。', 'run,3,walk,2;run,3,walk,2;run,3,walk,2;run,3,walk,2;run,3,walk,2;run,3,walk,2;', '向前摆动，手肘保持在身体两侧。这样可以让你保持一个舒适的节奏。你的腿也会相应作出调整。当你的身体适应了训练并且你的健康状况改善以后，步伐会自然而然地更大更快，但是现在，所有要注意的只是你的节奏和步伐。\n" +
                "提示：在训练日志上记录你的进程。坚持记日志可以帮助你找到任何受伤的根源。要记下每次训练课的感受，什么时候和在哪儿训练，你生活中发生了什么（比如，跑步之前因为小孩子哭闹而整夜没睡觉）和任何别的你想记录下的事情。这是树立目标和掌握进程的最好方式。记住要保持简单和诚实。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (5, '第4周：轻松的恢复周', 4, 0, null, '跑步3分钟。行走2分钟。共做6次。', 'run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;run,3;walk,2;', '跑步2分钟。行走2分钟。共做5次。', 'run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;run,2;walk,2;', '跑步2分钟。行走3分钟。共做6次。', 'run,2;walk,3;run,2;walk,3;run,2;walk,3;run,2;walk,3;run,2;walk,3;run,2;walk,3;', '从第1周开始你已经训练了不少了，在提高身体素质的同时你需要一些休息。还记得你参加第一堂训练课时的那种不确定吗？你现在应该对自己的舒服状态比较熟悉了，对你的节奏和步伐也更自信了。享受这个“轻松”的星期吧，继续保持放松和舒适的步伐。\n" +
                "提示：要一直尝试积极地思考。把注意力集中到好的事情而不是坏的事情上面。在这项训练的开始阶段，当你的身体开始适应新的强度水平时，你会遇到各种疼痛和痛苦。耐心一些，这是你进步过程中不可缺少的心态。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (6, '第5周：注意“拖着脚慢跑”', 5, 0, null, '跑步3分钟。行走1分钟。共做9次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '跑步2分钟。行走1分钟。共做8次。跑步2分钟。行走1分钟。共做8次。', 'run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;', '跑步3分钟。行走1分钟。共做8次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '经过上周的“恢复”，这周你应该对3分钟的“拖着脚慢跑”没有什么问题了。现在，最大的不同在于你的行走（恢复）时间减少到了1分钟，这段时间是非常快的，因此，“拖着脚慢跑”比以前更重要了。如果你感觉自己发出呼哧呼哧的声音，明显不能正常交谈了，就必须慢下来。\n" +
                "提示：找一个训练伙伴，这样在13周训练过程中你能得到一些建议。知道有人等着自己是非常有帮助的——你们可以互相激励、帮助对方完成训练课程，这种感觉是令人难以置信的。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (7, '第6周：增加训练量', 6, 0, null, '跑步5分钟。行走1分钟。共做7次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '跑步3分钟。行走1分钟。共做7次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '跑步3分钟。行走1分钟。共做10次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '在这个阶段，你可能会感觉累了。尽管这时候休息一下是非常诱惑人的，但是坚持之后应该很快就会感觉好很多了。从马路上下来，找一块更软的地面、草地或者小路继续训练，这是一种非常好的休息方式。新的路面状况可以为你的腿部提供一个很好的恢复机会。在那儿待一会儿，疲劳就会缓解。记得要控制好你的姿势和步伐，在必要的时候，慢下来以避免受伤。\n" +
                "提示：如果你感到疼痛，用冰敷。把水装进塑料杯然后冰冻。当你在训练前或训练后需要冰敷疼痛的部位时，剥开发泡胶就可以了。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (8, '中段检查', 0, 0, null, null, null, null, null, null, null, '这项13周训练计划的最终目标是让你安全舒适地完成10公里跑。现在你已经到达计划的一半了，这是一个很好的评估你感受的时机。要说实话，要知道每个人对训练的反应是不同的。\n" +
                "如果过去的训练让你感觉舒适的话，那就接着进行下面的训练吧。你的训练时间会继续增加，同时穿插的行走时间会明显减少。在13周计划临近结束的时候，你要做好完成一段10公里距离的跑步的准备，中间穿插极少时间的行走。  \n" +
                "如果你感觉完成训练非常困难，或者只是喜欢跑步和行走穿插在一起的训练，那么你可以选择“跑步行走”这个选项继续第6周以后的训练。你也要准备在13周计划临近结束时完成10公里的训练，但是你将用跑步和行走结合的方式而不仅仅是跑步来完成。实际上，你的每个训练段都不会超过10分钟，接着是一个短时间的行走段。\n" +
                "在训练进程中能自始至终感觉舒适的关键因素是：知道你可以选择“跑步行走”选项，这个选项在接下来的每周都会提供给你，当然这要看训练的感觉如何。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (9, '第7周：训练过了一半', 7, 1, '跑步选项', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步4分钟。行走1分钟。共做6次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步5分钟。行走1分钟。共做7次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '祝贺你！你已经完成计划的一半了，你已经了解到了许多你身体能够应对的事情。从本周起，一直有“跑步行走”选项供你选择，你可以在任何时候选择它。这周，要增强你的自信心，做一个5000米距离的训练测试。选择一个合适的地方来做一个距离精确的5000米训练课程，然后按那条路线完成你的预定训练内容。记住要保持放松，让步伐一直保持一致。把你的注意力集中到手臂的运动上面，然后你的腿部会跟随手臂的节奏运动。\n" +
                "提示：当你感觉自己精力充沛时，可以在有风的日子进行训练。可以迎风跑出去，顺风跑回家。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (10, '第7周：训练过了一半', 7, 2, '跑步行走选项', '跑步6分钟。行走1分钟。共做6次。', 'run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;', '跑步4分钟。行走1分钟。共做6次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步4分钟。行走1分钟。共做8次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '祝贺你！你已经完成计划的一半了，你已经了解到了许多你身体能够应对的事情。从本周起，一直有“跑步行走”选项供你选择，你可以在任何时候选择它。这周，要增强你的自信心，做一个5000米距离的训练测试。选择一个合适的地方来做一个距离精确的5000米训练课程，然后按那条路线完成你的预定训练内容。记住要保持放松，让步伐一直保持一致。把你的注意力集中到手臂的运动上面，然后你的腿部会跟随手臂的节奏运动。\n" +
                "提示：当你感觉自己精力充沛时，可以在有风的日子进行训练。可以迎风跑出去，顺风跑回家。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (11, '第8周：轻松的恢复周', 8, 1, '跑步选项', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步3分钟。行走1分钟。共做7次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '跑步5分钟。行走1分钟。共做6次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '你已经到达了13周计划的另一个重要节点：该训练计划每4周的最后一周都是一个恢复周，这一周的宗旨就是为了休息一下，特别是在你有任何不寻常的疼痛和痛苦的情况下。可以把其中的一节训练课换成低冲击的训练，比如在一个深水池中完成分段训练，这样可以让腿休息一下，但是仍然能锻炼心肺功能。\n" +
                "提示：在水池里面跑步可能比一圈圈游泳要有意思得多。和你的朋友们一起做——你们可以像在慢跑的时候一样交谈。为了挨过时间，你可以快慢交替进行。选择一个播放好听音乐的游泳池，你可以随着音乐节奏去训练。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (12, '第8周：轻松的恢复周', 8, 2, '跑步行走选项', '跑步5分钟。行走1分钟。共做7次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '跑步3分钟。行走1分钟。共做7次。', 'run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;run,3;walk,1;', '跑步2分钟。行走1分钟。共做12次。', 'run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;run,2;walk,1;', '你已经到达了13周计划的另一个重要节点：该训练计划每4周的最后一周都是一个恢复周，这一周的宗旨就是为了休息一下，特别是在你有任何不寻常的疼痛和痛苦的情况下。可以把其中的一节训练课换成低冲击的训练，比如在一个深水池中完成分段训练，这样可以让腿休息一下，但是仍然能锻炼心肺功能。\n" +
                "提示：在水池里面跑步可能比一圈圈游泳要有意思得多。和你的朋友们一起做——你们可以像在慢跑的时候一样交谈。为了挨过时间，你可以快慢交替进行。选择一个播放好听音乐的游泳池，你可以随着音乐节奏去训练。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (13, '第9周：回到训练中', 9, 1, '跑步选项', '跑步10分钟。行走1分钟。跑步15分钟。行走1分钟。跑步20分钟。行走1分钟。跑步10分钟。', 'run,10;walk,1;run,15;walk,1;run,20;walk,1;run,10;', '跑步5分钟。行走1分钟。共做6次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '现在又回到常规训练了。经过一周的恢复，你可以增加训练强度了。这周，跑步时间和训练时间都会增加，但是你已经为它们作好准备了。保持自信、强健和放松。让手臂控制你的节奏，最重要的是，要让你的步伐缓慢，跑步中仍然能够交谈。行走的部分现在对你来说只是心理上的休息了。\n" +
                "提示：由于训练时间变长，你需要尝试着给自己一些鼓励以保持动力。为这周的训练安排好路线，让别人在路线的终点帮你拿一套新衣服。可以安排在这之后吃点美味的零食或大餐来庆祝你完成了训练。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (14, '第9周：回到训练中', 9, 2, '跑步行走选项', '跑步6分钟。行走1分钟。共做8次。', 'run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;run,6;walk,1;', '跑步4分钟。行走1分钟。共做9次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步5分钟。行走1分钟。共做8次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '现在又回到常规训练了。经过一周的恢复，你可以增加训练强度了。这周，跑步时间和训练时间都会增加，但是你已经为它们作好准备了。保持自信、强健和放松。让手臂控制你的节奏，最重要的是，要让你的步伐缓慢，跑步中仍然能够交谈。行走的部分现在对你来说只是心理上的休息了。\n" +
                "提示：由于训练时间变长，你需要尝试着给自己一些鼓励以保持动力。为这周的训练安排好路线，让别人在路线的终点帮你拿一套新衣服。可以安排在这之后吃点美味的零食或大餐来庆祝你完成了训练。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (15, '第10周：漫长的一周', 10, 1, '跑步选项', '跑步10分钟。行走1分钟。跑步20分钟。行走1分钟。跑步30分钟。', 'run,10;walk,1;run,20;walk,1;run,30;walk,1;', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步20分钟。行走1分钟。跑步15分钟。行走1分钟。跑步10分钟。', 'run,20;walk,1;run,15;walk,1;run,10;', '这是漫长的一周，因为你要在跑步上面花更多的时间，但是跑步区段之间和平时一样有一分钟的行走时间。经过前几周的训练，你已经可以把注意力集中在手臂动作上面，这样可以保持你的节奏。记住，虽然你想跑得快一些，但是现在“速度”并不重要。这部分训练计划的所有的目的都是为了让你习惯冲击和距离，这对大多数人是最艰难的。要保持动力，以微笑来和疲劳作斗争。如果别人以微笑来回应你，你会感觉棒极了。\n" +
                "提示：聆听你的身体。留心它所告诉你的。如果你患上了感冒或流感，暂停训练一两天。在继续训练之前给自己一个恢复的机会。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (16, '第10周：漫长的一周', 10, 2, '跑步行走选项', '跑步8分钟。行走1分钟。共做7次。', 'run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;', '跑步4分钟。行走1分钟。共做9次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步5分钟。行走1分钟。共做8次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '这是漫长的一周，因为你要在跑步上面花更多的时间，但是跑步区段之间和平时一样有一分钟的行走时间。经过前几周的训练，你已经可以把注意力集中在手臂动作上面，这样可以保持你的节奏。记住，虽然你想跑得快一些，但是现在“速度”并不重要。这部分训练计划的所有的目的都是为了让你习惯冲击和距离，这对大多数人是最艰难的。要保持动力，以微笑来和疲劳作斗争。如果别人以微笑来回应你，你会感觉棒极了。\n" +
                "提示：聆听你的身体。留心它所告诉你的。如果你患上了感冒或流感，暂停训练一两天。在继续训练之前给自己一个恢复的机会。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (17, '第11周：树立信心', 11, 1, '跑步选项', '跑步40分钟。行走1分钟。跑步20分钟。', 'run,40;walk,1;run,20;', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步20分钟。行走1分钟。跑步15分钟。行走1分钟。跑步10分钟。', 'run,20;walk,1;run,15;walk,1;run,10;', '这周你需要比以前更多的激情，因为你的跑步距离和时间都达到了你目前的最大值。继续保持这种可以谈话的步伐的话，你现在已经可以跑得足够长了，已经可以满足10公里距离的训练要求了！如果你做的是跑步行走训练，你一次跑10分钟，训练时间已经达到了76分钟，这也是这项计划的一个重要节点。不管你选择哪种训练，你现在跑步时间都比行走时间要长得多。\n" +
                "提示：要在健康和没有受伤的状态下训练。要注意保持足够的睡眠以保证健康，膳食平衡，喝足够的水。随身带一个水瓶，随时都可以补充一些。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (18, '第11周：树立信心', 11, 2, '跑步行走选项', '跑步10分钟。行走1分钟。共做6次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步4分钟。行走1分钟。共做9次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步5分钟。行走1分钟。共做8次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '这周你需要比以前更多的激情，因为你的跑步距离和时间都达到了你目前的最大值。继续保持这种可以谈话的步伐的话，你现在已经可以跑得足够长了，已经可以满足10公里距离的训练要求了！如果你做的是跑步行走训练，你一次跑10分钟，训练时间已经达到了76分钟，这也是这项计划的一个重要节点。不管你选择哪种训练，你现在跑步时间都比行走时间要长得多。\n" +
                "提示：要在健康和没有受伤的状态下训练。要注意保持足够的睡眠以保证健康，膳食平衡，喝足够的水。随身带一个水瓶，随时都可以补充一些。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (19, '第12周：轻松的一周', 12, 1, '跑步选项', '跑步50分钟。', 'run,50;', '跑步10分钟。行走1分钟。共做3次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步15分钟。行走1分钟。跑步15分钟。行走1分钟。跑步10分钟。', 'run,15;walk,1;run,15;walk,1;run,10;', '你真棒！这是一个非常重要的恢复周。想象你完成了10公里训练，或者参加了10公里跑的赛事，最后冲向了终点。你能够做到！在这个阶段，要抵抗一些诱惑，比如“考考自己”能不能做一些超过训练课程安排的跑步内容。对你的准备要有信心，把最好的状态留给参加比赛的那天。如果你真想评估自己是否能够完成10公里距离的跑步，那么就跑8公里吧，这样你会感觉很棒，有继续跑下去的渴望。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (20, '第12周：轻松的一周', 12, 2, '跑步行走选项', '跑步8分钟。行走1分钟。共做6次。', 'run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;run,8;walk,1;', '跑步4分钟。行走1分钟。共做6次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '跑步5分钟。行走1分钟。共做7次。', 'run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;run,5;walk,1;', '你真棒！这是一个非常重要的恢复周。想象你完成了10公里训练，或者参加了10公里跑的赛事，最后冲向了终点。你能够做到！在这个阶段，要抵抗一些诱惑，比如“考考自己”能不能做一些超过训练课程安排的跑步内容。对你的准备要有信心，把最好的状态留给参加比赛的那天。如果你真想评估自己是否能够完成10公里距离的跑步，那么就跑8公里吧，这样你会感觉很棒，有继续跑下去的渴望。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (21, '第13周：祝贺！', 13, 1, '跑步选项', '跑步40分钟。', 'run,40;', '跑步10分钟。行走1分钟。共做3次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;', '10公里：跟着你的感觉跑，享受乐趣，注意开始不要跑太快。', 'km,10;', '你已经可以安全舒适地完成10公里长度的训练了，不管是采用跑步的方式还是跑步穿插行走的方式。为自己树立坚强的信心吧：艰难的训练已经完成了，现在是迈向最后荣耀的时候了。这周是非常美好而轻松的，你的肌肉和心理可以得到完全的恢复，这样你就会感觉休息够了而且完全准备好了。你做到了。');");


        db.execSQL("INSERT INTO \"run_plan\" VALUES (22, '第13周：祝贺！', 13, 2, '跑步行走选项', '跑步10分钟。行走1分钟。共做4次。', 'run,10;walk,1;run,10;walk,1;run,10;walk,1;run,10;walk,1;', '跑步4分钟。行走1分钟。共做6次。', 'run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;run,4;walk,1;', '10公里：跟着你的感觉去跑步和行走，享受乐趣，注意开始不要跑太快。', 'km,10;', '你已经可以安全舒适地完成10公里长度的训练了，不管是采用跑步的方式还是跑步穿插行走的方式。为自己树立坚强的信心吧：艰难的训练已经完成了，现在是迈向最后荣耀的时候了。这周是非常美好而轻松的，你的肌肉和心理可以得到完全的恢复，这样你就会感觉休息够了而且完全准备好了。你做到了。');");


        db.execSQL("CREATE TABLE \"run_record\" (\n" +
                "\"run_record_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"start_time\"  INTEGER,\n" +
                "\"end_time\"  INTEGER,\n" +
                "\"kilometer\"  REAL,\n" +
                "\"avg_speed\"  REAL,\n" +
                "\"trace_entity\"  TEXT,\n" +
                "\"usr_id\"  INTEGER\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

