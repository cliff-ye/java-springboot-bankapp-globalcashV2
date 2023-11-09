"use strict";

const featuresBtn = document.querySelector(".feature--btn");
const overlay = document.querySelector(".overlay");
const transactType = document.querySelector("#transact-type");
const transferCard = document.querySelector(".transfer-card");
const depositCard = document.querySelector(".dep-card");
const withdrawCard = document.querySelector(".withdraw-card");
const accountModal = document.querySelector(".accountModal");
const closeAcctModal = document.querySelector(".closeAcctModal");
const transactModal = document.querySelector(".transactModal");
const closeTransact = document.querySelector(".closeTransact");
const Alltransfers = document.querySelector(".Alltransfers");
const closeAlltransfers = document.querySelector(".closeAlltransfers");
const Alltransactions = document.querySelector(".Alltransactions");
const closeAlltransact = document.querySelector(".closeAlltransact");

//add feature btn events-event delegation
featuresBtn.addEventListener("click", function (e) {
  //console.log(e.target);
  e.preventDefault();

  const clicked = e.target.closest(".btn");
  // console.log(clicked.dataset.tab);

  if (!clicked) return;

  overlay.classList.remove("hidden");
  document
    .querySelector(`.f--${clicked.dataset.tab}`)
    .classList.remove("hidden");
});

//close button
const hideOverlay = function () {
  overlay.classList.add("hidden");
};

const closeMe = function (modal, btn) {
  btn.addEventListener("click", function (e) {
    e.preventDefault();
    modal.classList.add("hidden");
    hideOverlay();
  });
};

const closeByOverlay = function (modal) {
  overlay.addEventListener("click", function (e) {
    e.preventDefault();
    modal.classList.add("hidden");
    hideOverlay();
  });
};

closeMe(accountModal, closeAcctModal);
closeMe(transactModal, closeTransact);
closeMe(Alltransfers, closeAlltransfers);
closeMe(Alltransactions, closeAlltransact);

closeByOverlay(accountModal);
closeByOverlay(transactModal);
closeByOverlay(Alltransfers);
closeByOverlay(Alltransactions);

transactType.addEventListener("change", function (e) {
	e.preventDefault();
  //switch between cards
  switch (transactType.value) {
    case "":
      transferCard.classList.add("hidden");
      depositCard.classList.add("hidden");
      withdrawCard.classList.add("hiddden");
      break;

    case "transfer":
      transferCard.classList.remove("hidden");
      depositCard.classList.add("hidden");
      withdrawCard.classList.add("hidden");
      break;

    case "deposit":
      transferCard.classList.add("hidden");
      depositCard.classList.remove("hidden");
      withdrawCard.classList.add("hidden");
      break;

    case "withdraw":
      transferCard.classList.add("hidden");
      depositCard.classList.add("hidden");
      withdrawCard.classList.remove("hidden");
      break;
  }
});
