package com.tatak.footballapp.model.repository

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import com.tatak.footballapp.network.FootballRest

class MatchRepositoryImplUnitTest {

    @Mock
    lateinit var footballRest: FootballRest

    private lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = MatchRepositoryImpl(footballRest)
    }

    @Test
    fun getEventById() {
        matchRepositoryImpl.getEventById("123")
        verify(footballRest).getEventById("123")
    }

    @Test
    fun getUpcomingMatch() {
        matchRepositoryImpl.getUpcomingMatch("123")
        verify(footballRest).getUpcomingMatch("123")
    }

    @Test
    fun getFootballMatch() {
        matchRepositoryImpl.getFootballMatch("123")
        verify(footballRest).getLastmatch("123")
    }

}