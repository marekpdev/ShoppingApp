package com.marekpdev.shoppingapp.ui.paymentcard

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentPaymentCardBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class PaymentCardFragment : BaseFragment<PaymentCardState, PaymentCardAction, PaymentCardCommand, FragmentPaymentCardBinding>(R.layout.fragment_payment_card) {

    private val navArgs: PaymentCardFragmentArgs by navArgs()

    @Inject
    lateinit var paymentCardViewModelFactory: PaymentCardViewModel.Factory

    override val viewModel by viewModels<PaymentCardViewModel> {
        PaymentCardViewModel.provideFactory(
            assistedFactory = paymentCardViewModelFactory,
            paymentCardId = navArgs.paymentCardId
        )
    }

    override fun initLayout(binding: FragmentPaymentCardBinding) = with(binding){
        btnAdd.setOnClickListener { viewModel.dispatch(PaymentCardAction.AddPaymentCard) }
        btnUpdate.setOnClickListener { viewModel.dispatch(PaymentCardAction.UpdatePaymentCard) }

        val onContentChanged = {
            viewModel.dispatch(PaymentCardAction.OnContentChanged(
                cardNumber = tvCardNumber.text.toString(),
                cvc = tvCvcCode.text.toString(),
                expiryDate = tvExpiryDate.text.toString()
            ))
        }

        listOf(tvCardNumber, tvCvcCode, tvExpiryDate).forEach {
            it.doAfterTextChanged { onContentChanged() }
        }
    }

    override fun render(state: PaymentCardState) {
        binding.apply {
            tvHeader.text = when(state.mode){
                Mode.ADD -> "Add Payment Card"
                Mode.UPDATE -> "Update Payment Card"
            }

            pbLoading.visibility = if(state.loading) View.VISIBLE else View.GONE

            tvCardNumber.setTextIfDifferent(state.cardNumber)
            tvCvcCode.setTextIfDifferent(state.cvc)
            tvExpiryDate.setTextIfDifferent(state.expiryDate)

            tvCardNumber.isEnabled = !state.loading
            tvCvcCode.isEnabled = !state.loading
            tvExpiryDate.isEnabled = !state.loading

            btnAdd.visibility = if(state.mode == Mode.ADD) View.VISIBLE else View.GONE
            btnUpdate.visibility = if(state.mode == Mode.UPDATE) View.VISIBLE else View.GONE
        }
    }

    override fun onCommand(command: PaymentCardCommand) {
        when(command){
            is PaymentCardCommand.GoBackToPaymentMethodsScreen -> {
                findNavController().navigateUp()
            }
        }
    }

}