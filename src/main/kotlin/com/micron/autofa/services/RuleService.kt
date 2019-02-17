package com.micron.autofa.services

import com.micron.autofa.models.QueryRule

interface RuleService {
    fun exists(ruleName: String): Boolean
    fun add(rule: QueryRule)
    fun remove(ruleName: String)
    fun update(rule: QueryRule)
}

class FakeRuleService : RuleService {
    override fun exists(ruleName: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(rule: QueryRule) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(ruleName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(rule: QueryRule) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}