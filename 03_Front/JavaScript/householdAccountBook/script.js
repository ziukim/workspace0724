/*
  데이터 구조 예시
  {
    id: 1234567890,            // 고유 ID
    description: "점심값",     // 내용
    amount: 8000,              // 금액(양수)
    type: "expense",           // "income" | "expense"
    date: "2025-09-22"         // ISO yyyy-mm-dd
  }
*/

(() => {
  'use strict';

  const LS_KEY = 'ledger-items:v1';
  const FILTER_KEY = 'ledger-filter:v1';

  /** @type {Array} */
  let items = JSON.parse(localStorage.getItem(LS_KEY)) || [];
  let currentType = 'income'; // 입력 패널 수입/지출 선택
  let filterState = localStorage.getItem(FILTER_KEY) || 'all';

  // DOM
  const $ = (s) => document.querySelector(s);
  const listEl = $('#list');

  // 입력
  const descEl = $('#desc');
  const amountEl = $('#amount');
  const btnIncome = $('#btn-income');
  const btnExpense = $('#btn-expense');
  const addBtn = $('#add-btn');

  // 요약/헤더
  const balanceHeaderEl = $('#balance');
  const sumIncomeEl = $('#sum-income');
  const sumExpenseEl = $('#sum-expense');
  const sumBalanceEl = $('#sum-balance');

  // 필터
  const filterBtns = document.querySelectorAll('.filter-buttons button');

  // ============== Utils ==============
  const today = () => new Date().toISOString().slice(0,10);
  const uid = () => Date.now();
  const toCurr = (n) => Number(n).toLocaleString('ko-KR') + '원';

  const onlyDigits = (str) => str.replace(/[^\d]/g, '');
  function parseAmount(input){
    // "1,000", "  2000 " 등 허용 → 양의 정수
    const cleaned = onlyDigits(String(input));
    if (cleaned === '') return NaN;
    return Number(cleaned);
  }

  function save(){
    localStorage.setItem(LS_KEY, JSON.stringify(items));
  }

  // ============== Init ==============
  function init(){
    // 타입 버튼
    btnIncome.addEventListener('click', () => setType('income'));
    btnExpense.addEventListener('click', () => setType('expense'));

    // 금액 입력 시 자동 콤마 표시(보기용)
    amountEl.addEventListener('input', (e)=>{
      const caret = amountEl.selectionStart;
      const val = onlyDigits(e.target.value);
      amountEl.value = val ? Number(val).toLocaleString('ko-KR') : '';
      // caret 복잡도는 과제 범위상 생략 (간단 처리)
    });

    // 추가
    addBtn.addEventListener('click', addItem);
    descEl.addEventListener('keydown', (e)=> { if (e.key === 'Enter') addItem(); });
    amountEl.addEventListener('keydown', (e)=> { if (e.key === 'Enter') addItem(); });

    // 필터 버튼
    filterBtns.forEach(btn=>{
      btn.classList.toggle('active', btn.dataset.filter === filterState);
      btn.addEventListener('click', ()=> setFilter(btn.dataset.filter));
    });

    render();
  }

  // ============== Type / Filter ==============
  function setType(t){
    currentType = t;
    btnIncome.classList.toggle('active', t==='income');
    btnExpense.classList.toggle('active', t==='expense');
  }

  function setFilter(f){
    filterState = f;
    localStorage.setItem(FILTER_KEY, f);
    filterBtns.forEach(b=> b.classList.toggle('active', b.dataset.filter === f));
    render();
  }

  // ============== CRUD ==============
  function addItem(){
    const desc = descEl.value.trim();
    const amt = parseAmount(amountEl.value);

    if (!desc){
      alert('내용을 입력하세요.');
      descEl.focus();
      return;
    }
    if (!Number.isFinite(amt) || amt <= 0){
      alert('금액은 0보다 큰 숫자만 허용됩니다.');
      amountEl.focus();
      return;
    }

    const item = {
      id: uid(),
      description: desc,
      amount: amt,                  // 항상 양수 저장
      type: currentType,            // "income" | "expense"
      date: today(),
    };
    items.push(item);
    save();

    // 입력 리셋
    descEl.value = '';
    amountEl.value = '';
    descEl.focus();

    render();
  }

  function removeItem(id){
    items = items.filter(it => it.id !== id);
    save();
    render();
  }

  // ============== View ==============
  function getFilteredItems(){
    if (filterState === 'all') return items;
    return items.filter(it => it.type === filterState);
  }

  function render(){
    // 목록
    listEl.innerHTML = '';
    const list = getFilteredItems();

    if (list.length === 0){
      const li = document.createElement('li');
      li.className = 'empty';
      li.textContent = '내역이 없습니다.';
      listEl.appendChild(li);
    } else {
      for (const it of list){
        const li = document.createElement('li');
        li.className = 'item';

        const info = document.createElement('div');
        const title = document.createElement('div');
        title.textContent = it.description;

        const meta = document.createElement('div');
        meta.className = 'meta';
        meta.textContent = new Date(it.date).toLocaleDateString('ko-KR', {year:'numeric', month:'long', day:'numeric'});

        const badge = document.createElement('span');
        badge.className = 'badge ' + (it.type === 'expense' ? 'expense' : 'income');
        badge.textContent = (it.type === 'income' ? '수입' : '지출');
        title.appendChild(badge);

        info.appendChild(meta);
        info.appendChild(title);

        const amt = document.createElement('div');
        const sign = it.type === 'income' ? '+' : '-';
        amt.className = 'amount ' + (it.type === 'income' ? 'pos' : 'neg');
        amt.textContent = `${sign}${toCurr(it.amount)}`;

        const del = document.createElement('button');
        del.className = 'del';
        del.textContent = '삭제';
        del.addEventListener('click', ()=> removeItem(it.id));

        li.appendChild(info);
        li.appendChild(amt);
        li.appendChild(del);
        listEl.appendChild(li);
      }
    }

    // 요약/잔액
    let income = 0, expense = 0;
    for (const it of items){
      if (it.type === 'income') income += it.amount;
      else expense += it.amount;
    }
    const balance = income - expense;

    sumIncomeEl.textContent = toCurr(income);
    sumExpenseEl.textContent = toCurr(expense);
    sumBalanceEl.textContent = toCurr(balance);

    balanceHeaderEl.textContent = toCurr(balance);
    balanceHeaderEl.style.color = balance >= 0 ? '#22c55e' : '#ef4444';
  }

  document.addEventListener('DOMContentLoaded', init);
})();
