package ir.liyanamarket.predictlive


import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.adapter.PredictAdapter
import ir.liyanamarket.predictlive.adapter.RankingAdapter
import ir.liyanamarket.predictlive.fragment.*
import ir.liyanamarket.predictlive.model.changeprofile.ApiConnectToChangeProfile
import ir.liyanamarket.predictlive.model.match.ApiConnectToSelectMatch
import ir.liyanamarket.predictlive.model.predict.ApiConnectSelectPredict
import ir.liyanamarket.predictlive.model.predict.ApiConnectinsertPredict
import ir.liyanamarket.predictlive.model.register.ApiConnectRegister
import ir.liyanamarket.predictlive.model.sendcode.ApiConnectSendCode
import ir.liyanamarket.predictlive.model.user.ApiConnectUsers
import ir.liyanamarket.predictlive.model.validatephonenumber.ApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.presenter.changeprofile.PresenterApiconnectChangeProfile
import ir.liyanamarket.predictlive.presenter.match.PresenterApiConnectSelectMatch
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectSelectPredict
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectinsertPredict
import ir.liyanamarket.predictlive.presenter.register.PresenterApiConnectRegister
import ir.liyanamarket.predictlive.presenter.sendcode.PresenterApiConnectSendCode
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import ir.liyanamarket.predictlive.presenter.validatephonenumber.PresenterApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.utils.CheckValidateInputPhoneNumber
import ir.liyanamarket.predictlive.utils.EncodeAndDecodeImage
import ir.liyanamarket.predictlive.utils.SaveLoginInfo
import org.koin.dsl.module

var appmadules= module {
    single {
        ApiConnectUsers() }
    single { PresenterApiConnectUser() }
    single { FragmentProgressBar() }
    single { FragmentHome() }
    single { Picasso.with(get()) }
    single { FragmentRanking(get()) }
    single { RankingAdapter(get()) }
    single { ApiConnectSelectPredict() }
    single { PresenterApiConnectSelectPredict() }
    single { ApiConnectToSelectMatch() }
    single { PresenterApiConnectSelectMatch() }
    single { PredictAdapter(get()) }
    single { ApiConnectinsertPredict() }
    single { PresenterApiConnectinsertPredict() }
    single { ApiConnectToChangeProfile() }
    single { PresenterApiconnectChangeProfile() }
    single { EncodeAndDecodeImage() }
    single { FragmentSetting() }
    single { SaveLoginInfo(get()) }
    single { CheckValidateInputPhoneNumber(get()) }
    single { ApiConnectValidatePhoneNumber() }
    single { PresenterApiConnectValidatePhoneNumber() }
    single { ApiConnectSendCode() }
    single { PresenterApiConnectSendCode() }
    single { ApiConnectRegister() }
    single { PresenterApiConnectRegister() }
}