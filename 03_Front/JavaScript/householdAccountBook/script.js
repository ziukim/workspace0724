(() => { //즉시실행 함수 전역변수 오염방지

  const LS_KEY = 'ledger-items:v1';
  const FILTER_KEY = 'ledger-filter:v1';

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

  // 유틸함수
  const today = () => new Date().toISOString().slice(0,10); // 오늘날짜 yy mm dd 로 반환
  const uid = () => Date.now();
  const toCurr = (n) => Number(n).toLocaleString('ko-KR') + '원'; //천단위 끊어서 표시

  const onlyDigits = (str) => str.replace(/[^\d]/g, ''); //숫자가 아닌 모든 문자를 공백으로 치환
  function parseAmount(input){
    // 계산위한 숫자 표시 형식 변경
    const cleaned = onlyDigits(String(input));
    if (cleaned === '') return NaN;
    return Number(cleaned);
  }

  // 메모리 로컬저장
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
    });

    // 클릭, 엔터로 추가
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

  // ============== 타입/필터상태갱신 ==============
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

  // ============== 추가/삭제 ==============
  function addItem(){
    const desc = descEl.value.trim();
    const amt = parseAmount(amountEl.value); //숫자검증

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
    items.push(item); //배열 끝에 저장
    save();

    // 초기화
    descEl.value = '';
    amountEl.value = '';
    descEl.focus();

    render();
  }

  function removeItem(id){
    items = items.filter(it => it.id !== id); // 원본 배열을splice 하는 것 보다 읽기, 디버깅이 명료해짐
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
      li.textContent = '내역이 없습니다.'; // XSS 방지 : textContent
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
