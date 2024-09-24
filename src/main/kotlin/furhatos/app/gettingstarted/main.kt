package furhatos.app.gettingstarted
import furhatos.app.gettingstarted.flow.Conversation
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill


class GettingstartedSkill : Skill() {
    override fun start() {
        Flow().run(Conversation)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}

