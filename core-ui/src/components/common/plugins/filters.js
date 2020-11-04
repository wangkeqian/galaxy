export function dateFormat(dateStr){
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