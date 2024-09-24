package furhatos.app.caregiver
import furhatos.app.caregiver.flow.Conversation
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill


class CaregiverSkill : Skill() {
    override fun start() {
        Flow().run(Conversation)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}

