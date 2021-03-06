export function dateFormat(dateStr){
  let df = new Date(dateStr);
  let year = df.getFullYear();
  let month = df.getMonth()+1;
  let day = df.getDate();
  let hours = df.getHours();
  let minuter = df.getMinutes();
  let second = df.getSeconds().toString().padStart(2,'0');
  //return year+'-'+month+'-'+day;
  return `${year}-${month}-${day}`;
}
export function datetimeFormat(dateStr){
  let df = new Date(dateStr);
  let year = df.getFullYear();
  let month = df.getMonth()+1;
  let day = df.getDate();
  let hours = df.getHours();
  let minuter = df.getMinutes();
  let second = df.getSeconds().toString().padStart(2,'0');
  //return year+'-'+month+'-'+day;
  return `${year}-${month}-${day}  ${hours}:${minuter}:${second}`;
}
export function contextSpliter(str){
  if(typeof(str) == 'string' && str.length > 150 ){
    str = str.substring(0,151)
  }
  return str
}
export function titleSpliter(str){
  if(typeof(str) == 'string' && str.length > 15 ){
    str = str.substring(0,15) + '...'
  }
  return str
}