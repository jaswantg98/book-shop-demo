for i in $(seq 1 $no_of_pairs);
do
# Change the path according to your batch path
  GIT_PUSH_URI="https://gitlab.com/tw-he-dev-bootcamp/$root_sub_group_name/pair-$(printf "%02d" $i)/$repo_name.git +master:master"
  echo $GIT_PUSH_URI
  git push $GIT_PUSH_URI
done

