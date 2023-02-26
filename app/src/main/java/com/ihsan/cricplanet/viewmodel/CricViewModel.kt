package com.ihsan.cricplanet.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ihsan.cricplanet.model.LeagueIncludeSeasons
import com.ihsan.cricplanet.model.season.SeasonByIdIncludeLeague
import com.ihsan.cricplanet.model.season.SeasonByIdIncludeLeagueTable
import com.ihsan.cricplanet.model.fixture.FixtureByIdWithDetails
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForLiveCard
import com.ihsan.cricplanet.model.player.PlayerCard
import com.ihsan.cricplanet.model.player.PlayerDetails
import com.ihsan.cricplanet.model.team.GlobalTeamRanking
import com.ihsan.cricplanet.repository.CricRepository
import com.ihsan.cricplanet.roomdb.dao.CricDao
import com.ihsan.cricplanet.roomdb.data.DataConverter
import com.ihsan.cricplanet.roomdb.db.CricPlanetDatabase
import kotlinx.coroutines.*
import retrofit2.HttpException

@OptIn(DelicateCoroutinesApi::class)
class CricViewModel(application: Application) : AndroidViewModel(application) {
    //Initialize repository object
    private val repository: CricRepository

    //Dao Initialize
    private var CricDao: CricDao

    val getSeasonDB: LiveData<List<SeasonByIdIncludeLeagueTable>>

    //Fixtures LiveData Holder
    private val _upcomingMatchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val upcomingMatchFixture: LiveData<List<FixtureIncludeForCard>> = _upcomingMatchFixture
    private val _recentMatchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val recentMatchFixture: LiveData<List<FixtureIncludeForCard>> = _recentMatchFixture
    private val _matchFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val matchFixture: LiveData<List<FixtureIncludeForCard>> = _matchFixture
    private val _todayFixture = MutableLiveData<List<FixtureIncludeForCard>>()
    val todayFixture: LiveData<List<FixtureIncludeForCard>> = _todayFixture
    private val _liveFixture = MutableLiveData<List<FixtureIncludeForLiveCard>>()
    val liveFixture: LiveData<List<FixtureIncludeForLiveCard>> = _liveFixture
    private val _fixtureByIdWithDetails = MutableLiveData<FixtureByIdWithDetails>()
    val fixtureByIdWithDetails: LiveData<FixtureByIdWithDetails> = _fixtureByIdWithDetails
    //Player LiveData Holder
    private val _player = MutableLiveData<List<PlayerCard>>()
    val player: LiveData<List<PlayerCard>> = _player
    private val _playerDetails = MutableLiveData<PlayerDetails>()
    val playerDetails: LiveData<PlayerDetails> = _playerDetails
    //team LiveData Holder
    private val _teamRanking = MutableLiveData<List<GlobalTeamRanking>>()
    val teamRanking: LiveData<List<GlobalTeamRanking>> = _teamRanking
    //league LiveData Holder
    private val _league = MutableLiveData<List<LeagueIncludeSeasons>>()
    val league: LiveData<List<LeagueIncludeSeasons>> = _league
    private val _leagueById = MutableLiveData<LeagueIncludeSeasons>()
    val leagueById: LiveData<LeagueIncludeSeasons> = _leagueById
    //season LiveData Holder
    private val _seasonById = MutableLiveData<SeasonByIdIncludeLeague>()
    val seasonById: LiveData<SeasonByIdIncludeLeague> = _seasonById

    init {
        //Getting dao instance
        CricDao = CricPlanetDatabase.getDatabase(application).CricDao()
        //Assigning dao object to repository instance
        repository = CricRepository(CricDao)
        getSeasonDB = repository.readSeason()
        getUpdateSeasonApi()
    }

    //RoomDB Call
    suspend fun storeSeasonLocal(listSeason: List<SeasonByIdIncludeLeague>?) {
        if (listSeason == null) {
            Log.d("cricStoreLocalApi", "SeasonApi Size null return: null")
            return
        }
        Log.d("teamApi", "Team Size: ${listSeason.size}")
        repository.storeSeasonLocal(DataConverter().getSeasonTable(listSeason))
    }



    fun getSeasonByIdLocal(id:Int): LiveData<SeasonByIdIncludeLeagueTable> {
        return repository.readSeasonById(id)
    }


    //Api Call
    private fun getUpdateSeasonApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    storeSeasonLocal(repository.getSeasonApi())
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModel", "getUpdateSeasonApi: $e")
                }
            }
        }
    }
    fun getTeamRanking() {
        GlobalScope.launch {
            viewModelScope.launch{
                try {
                    _teamRanking.value=repository.getTeamRankingApi()
                    Log.d("cricTeamViewmodel", "getTeamRanking: ${teamRanking.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricteamCatch", "getTeam: $e")
                }
            }
        }
    }

    fun getFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _matchFixture.value = repository.getFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getFixture: ${matchFixture.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getFixture: $e")
                }
            }
        }
    }

    fun getFixturesByIdApi(Id: Int) {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _fixtureByIdWithDetails.value = repository.getFixturesByIdApi(Id)
                    Log.d("cricViewModel", "viewModel Api getFixtureById: ${fixtureByIdWithDetails.value?.id}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getFixtureById: $e")
                }
            }
        }
    }

    fun getLiveFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _liveFixture.value = repository.getLiveFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getLiveFixture: ${liveFixture.value}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getLiveFixture: $e")
                }
            }
        }
    }

    fun getTodayFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _todayFixture.value = repository.getTodayFixturesApi()
                    Log.d("cricViewModel", "viewModel Api getTodayFixture: ${todayFixture.value?.size}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getTodayFixture: $e")
                }
            }
        }
    }

    fun getUpcomingFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _upcomingMatchFixture.value = repository.getUpcomingFixturesApi()
                    Log.d(
                        "cricViewModel",
                        "viewModel Api getUpcomingFixture: ${upcomingMatchFixture.value?.size}"
                    )
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "getUpcomingFixture: $e")
                }
            }
        }
    }

    fun getRecentFixturesApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _recentMatchFixture.value = repository.getRecentFixturesApi()
                    Log.d(
                        "cricViewModel",
                        "viewModel Api getRecentFixture: ${recentMatchFixture.value?.size}"
                    )
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getRecentFixture: $e")
                }
            }
        }
    }
    fun getPlayersApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _player.value = repository.getPlayersApi()
                } catch (e: HttpException) {
                    Log.d("cricViewModelCatch", "viewModel Api getPlayerCard: $e")
                } catch (e: Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getPlayerCard: $e")
                }
            }
        }
    }

    fun getPlayersByIdApi(Id: Int) {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _playerDetails.value = repository.getPlayersByIdApi(Id)
                    Log.d("cricViewModel", "viewModel Api getPlayersById: ${fixtureByIdWithDetails.value?.id}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getPlayersById: $e")
                }
            }
        }
    }
    fun getLeagueApi() {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _league.value = repository.getLeaguesApi()
                    Log.d(
                        "cricViewModel",
                        "viewModel Api getLeague: ${recentMatchFixture.value?.size}"
                    )
                } catch (e: HttpException) {
                    Log.d("cricViewModelCatch", "viewModel Api getLeague: $e")
                } catch (e: Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getLeague: $e")
                }
            }
        }
    }

    fun getLeagueByIdApi(Id: Int) {
        GlobalScope.launch {
            viewModelScope.launch {
                try {
                    _leagueById.value = repository.getLeaguesByIdApi(Id)
                    Log.d("cricViewModel", "viewModel Api getLeagueById: ${leagueById.value?.id}")
                } catch (e: java.lang.Exception) {
                    Log.d("cricViewModelCatch", "viewModel Api getLeagueById: $e")
                }
            }
        }
    }
}